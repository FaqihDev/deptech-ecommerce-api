package com.jamsirat.atmapi.service.impl;

import com.jamsirat.atmapi.Mapper.ResponseTransactionHistoryMapper;
import com.jamsirat.atmapi.Mapper.ResponseTransactionMapper;
import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.order.TransactionRequestDto;
import com.jamsirat.atmapi.dto.request.order.TransactionRequestProductDto;
import com.jamsirat.atmapi.dto.response.order.ResponseTransactionDto;
import com.jamsirat.atmapi.dto.response.order.ResponseTransactionHistoryDto;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.exception.HandlerJwtExpiredTokenException;
import com.jamsirat.atmapi.exception.OutOfStockException;
import com.jamsirat.atmapi.model.auth.User;
import com.jamsirat.atmapi.model.inventory.Product;
import com.jamsirat.atmapi.model.order.OrderSalesItem;
import com.jamsirat.atmapi.model.order.OrderSales;
import com.jamsirat.atmapi.repository.ICategoryRepository;
import com.jamsirat.atmapi.repository.IProductRepository;
import com.jamsirat.atmapi.repository.IOrderSalesHistory;
import com.jamsirat.atmapi.repository.IUserRepository;
import com.jamsirat.atmapi.service.ITransactionHistoryService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.ExceptionMessage;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperExceptionMessage;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.SuccessMessage;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperSuccessMessage;
import com.jamsirat.atmapi.statval.enumeration.ETransactionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements ITransactionHistoryService {

    private final IOrderSalesHistory orderSalesHistory;
    private final ResponseTransactionMapper transactionMapper;
    private final ResponseTransactionHistoryMapper transactionHistoryMapper;
    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;
    private final IUserRepository userRepository;


    @Override
    @Transactional
    public HttpResponse<?> processTransaction(HttpServletRequest httpServletRequest, TransactionRequestDto request) {

        List<OrderSalesItem> orderSalesItems = new ArrayList<>();
        List<Long> productIds = request.getRequestProduct().stream().
                map(TransactionRequestProductDto::getProductId).toList();
        var products = productRepository.findAllById(productIds);
        if (products.isEmpty()) {
            throw new DataNotFoundException("Products does not exist","Please check product id");
        }

        var userX = generateToken(httpServletRequest);
        for (TransactionRequestProductDto requestProduct : request.getRequestProduct()) {
            Product product = products.stream()
                    .filter(rp -> rp.getId().equals(requestProduct.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new DataNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND_EXCEPTION,DeveloperExceptionMessage.PRODUCT_NOT_FOUND_EXCEPTION));
            var category = categoryRepository.findByProducts(product);

            validateStock(product,List.of(requestProduct),request);
            performTransaction(product,List.of(requestProduct),request);

            if (category.isPresent()) {
                    OrderSalesItem orderSalesItem = OrderSalesItem
                        .builder()
                        .products(products)
                        .productName(product.getProductName())
                        .categoryName(category.get().getCategoryName())
                        .quantity(requestProduct.getQuantity())
                        .price(product.getPrice().multiply(BigDecimal.valueOf(requestProduct.getQuantity())))
                        .build();
                    orderSalesItems.add(orderSalesItem);
            }
        }

        OrderSales orderSales = OrderSales
                .builder()
                .orderSalesItem(orderSalesItems)
                .user(userX)
                .publicId(generateTransactionId())
                .transactionType(request.getType())
                .build();

        orderSalesItems.forEach(orderSalesItem -> orderSalesItem.setOrderSales(orderSales));
        orderSalesHistory.save(orderSales);
        ResponseTransactionDto response = transactionMapper.convert(orderSales);
        return HttpResponse.buildHttpResponse(SuccessMessage.TRANSACTION_SUCCESS,DeveloperSuccessMessage.TRANSACTION_SUCCESS, HttpStatus.CREATED,HttpStatus.CREATED.value(), response);
    }

    @Override
    public HttpResponse<?> listTransactionHistory() {
        var orderSales = orderSalesHistory.findAll();
        List <ResponseTransactionHistoryDto> response = transactionHistoryMapper.entitiesIntoDTOs(orderSales);
        return HttpResponse
                .buildHttpResponse(SuccessMessage.TRANSACTION_HISTORY,DeveloperSuccessMessage.TRANSACTION_HISTORY,HttpStatus.OK,HttpStatus.OK.value(),response);

    }

    @Transactional
    private void validateStock (Product product, List<TransactionRequestProductDto> requestProducts, TransactionRequestDto request) {
        TransactionRequestProductDto requestProduct = requestProducts.stream()
                .filter(rp -> rp.getProductId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(requestProduct)) {
            throw new DataNotFoundException("Product is not found in request","Please check your request");
        }

        if (request.getType().equals(ETransactionType.STOCK_OUT) && product.getStockProduct() < requestProduct.getQuantity()) {
            log.info("Stock with productId {} is {} : ",product.getId(),product.getStockProduct());
            throw new OutOfStockException(ExceptionMessage.OUT_OF_STOCK_EXCEPTION,DeveloperExceptionMessage.OUT_OF_STOCK_EXCEPTION);
        }

    }

    private void performTransaction (Product product, List<TransactionRequestProductDto> requestProducts, TransactionRequestDto request) {
        TransactionRequestProductDto requestProduct =
                requestProducts.stream()
                        .filter(rp -> rp.getProductId().equals(product.getId()))
                        .findFirst()
                        .orElse(null);

                if (Objects.isNull(requestProduct)) {
                    throw new DataNotFoundException("Product is not found in request","Please make sure your request");
                }

                if (request.getType().equals(ETransactionType.STOCK_OUT)) {
                    product.setStockProduct(product.getStockProduct() - requestProduct.getQuantity());
                } else if (request.getType().equals(ETransactionType.STOCK_IN)) {
                    product.setStockProduct(product.getStockProduct() + requestProduct.getQuantity());
                }
                productRepository.save(product);

    }

    private String generateTransactionId() {
        LocalDateTime now = LocalDateTime.now();
        String timeStamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        return "TRX-" + timeStamp;
    }

    private User generateToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                try {
                   User user = userRepository.findByToken(token);
                    if (user == null) {
                        throw new UsernameNotFoundException("User not found for token: " + token);
                    }
                    return user;

                } catch (Exception e) {
                    throw new HandlerJwtExpiredTokenException("JWT Token has expired or invalid");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate token", e);
        }

        return null;
    }
}
