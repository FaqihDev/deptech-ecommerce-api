package com.jamsirat.atmapi.Mapper;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.order.ResponseTransactionDto;
import com.jamsirat.atmapi.model.order.OrderSalesItem;
import com.jamsirat.atmapi.model.order.OrderSales;
import com.jamsirat.atmapi.repository.IOrderSalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ResponseTransactionMapper extends ADATAMapper<OrderSales, ResponseTransactionDto> {

    private final ResponseTransactionProductMapper responseTransactionProductMapper;
    private final IOrderSalesItemRepository orderSalesItemRepository;

    @Override
    public ResponseTransactionDto convert(OrderSales orderSales) {

        List<OrderSalesItem> orderSalesItems = orderSalesItemRepository.findByTransactionId(orderSales.getId());
        BigDecimal totalPrice = orderSalesItems.stream()
                .map(OrderSalesItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return ResponseTransactionDto.builder()
                .transactionId(orderSales.getPublicId())
                .transactionProducts(responseTransactionProductMapper.entitiesIntoDTOs(orderSalesItems))
                .type(orderSales.getTransactionType())
                .userId(orderSales.getUser().getId())
                .transactionTime(LocalDateTime.now())
                .totalPrice(totalPrice)
                .build();
    }

}
