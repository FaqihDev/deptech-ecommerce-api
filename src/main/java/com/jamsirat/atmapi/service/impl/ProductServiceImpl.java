package com.jamsirat.atmapi.service.impl;


import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.product.RequestAddProductDto;
import com.jamsirat.atmapi.dto.request.product.RequestUpdateProductDto;
import com.jamsirat.atmapi.dto.response.product.ResponseAddProductDto;
import com.jamsirat.atmapi.dto.response.product.ResponseDetailProductDto;
import com.jamsirat.atmapi.dto.response.product.ResponseUpdateProductDto;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.model.inventory.Product;
import com.jamsirat.atmapi.repository.ICategoryRepository;
import com.jamsirat.atmapi.repository.IProductRepository;
import com.jamsirat.atmapi.service.IProductService;
import com.jamsirat.atmapi.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.SuccessMessage;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperSuccessMessage;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    @Override
    public HttpResponse<Object> addProduct(RequestAddProductDto request) {

        var categoryProduct = categoryRepository.findById(request.getProductCategoryId()).orElseThrow(() ->
                new DataNotFoundException(String.format("Category with id %s is not exist",request.getProductCategoryId()),
                                                        "Please make sure category is correct"));
            var product = Product.builder()
                        .stockProduct(request.getStockProduct())
                        .productName(request.getProductName())
                        .image(request.getImage())
                        .descriptionProduct(request.getDescriptionProduct())
                        .productCategory(categoryProduct)
                        .build();
        productRepository.save(product);
        ResponseAddProductDto data = MapperUtil.parse(product,ResponseAddProductDto.class,MatchingStrategies.STRICT);
        data.setProductCategoryName(categoryProduct.getCategoryName());
        return HttpResponse.buildHttpResponse(DeveloperSuccessMessage.DATA_ADDED_SUCCESSFULLY,
                SuccessMessage.DATA_ADDED_SUCCESSFULLY,
                HttpStatus.CREATED,
                HttpStatus.CREATED.value(),
                data);
    }

    @Override
    public HttpResponse<List<ResponseDetailProductDto>> getListProduct() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            return HttpResponse.noContent();
        }

        List<ResponseDetailProductDto> listProduct = products.stream()
                .map(product -> ResponseDetailProductDto.builder()
                        .id(product.getId())
                        .stockProduct(product.getStockProduct())
                        .productName(product.getProductName())
                        .descriptionProduct(product.getDescriptionProduct())
                        .categoryName(product.getProductCategory().getCategoryName())
                        .build())
                .collect(Collectors.toList());

        return HttpResponse.buildHttpResponse(DeveloperSuccessMessage.DATA_FETCH_SUCCESSFULLY,
                SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                HttpStatus.OK,
                HttpStatus.OK.value(),
                listProduct);

    }

    @Override
    public HttpResponse<Object> updateProduct(RequestUpdateProductDto request) {
        var product = productRepository.findById(request.getId()).orElseThrow(() ->  new DataNotFoundException(String.format("Product with id %d is not exist", request.getId()),"please check again your Product id"));
        var categoryProduct = categoryRepository.findById(request.getProductCategoryId()).orElseThrow(() -> new DataNotFoundException(String.format("category  with product id %d is not exist", product.getId()),"please check again your Product id"));
        product.setProductName(request.getProductName());
        product.setDescriptionProduct(request.getDescriptionProduct());
        product.setStockProduct(request.getStockProduct());
        product.setImage(request.getImage());
        product.setProductCategory(categoryProduct);
        productRepository.save(product);
        ResponseUpdateProductDto responseData =  MapperUtil.parse(product, ResponseUpdateProductDto.class, MatchingStrategies.STRICT);
        responseData.setCategoryName(categoryProduct.getCategoryName());
        return HttpResponse.buildHttpResponse(DeveloperSuccessMessage.DATA_UPDATED_SUCCESSFULLY,
                SuccessMessage.DATA_UPDATED_SUCCESSFULLY,
                 HttpStatus.OK,
                 HttpStatus.OK.value(),
                 responseData);

        }

    @Override
    public HttpResponse<Object> deleteProduct(Long productId) {
        var product = productRepository.findById(productId).orElseThrow(() -> new DataNotFoundException(String.format("Product with id %d is not exist", productId),"please check again your Product id"));
        product.setIsDeleted(true);
        productRepository.save(product);
        return HttpResponse.buildHttpResponse(DeveloperSuccessMessage.DATA_DELETED_SUCCESSFULLY,
                SuccessMessage.DATA_DELETED_SUCCESSFULLY,
                HttpStatus.OK,
                HttpStatus.OK.value(),
                null);
    }

    @Override
    public HttpResponse<Object> getDetailProduct(Long productId) {
        var product = productRepository.findById(productId);
        if (product.isEmpty()) {
            return HttpResponse.noContent();
        }
        Product productOptional = product.get();
        ResponseDetailProductDto data = MapperUtil.parse(productOptional, ResponseDetailProductDto.class, MatchingStrategies.STRICT);
        data.setCategoryName(Objects.isNull(productOptional.getProductCategory())
                ? null
                : productOptional.getProductCategory().getCategoryName());
        return HttpResponse.buildHttpResponse(SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                HttpStatus.OK,
                HttpStatus.OK.value(),
                data);
    }

}
