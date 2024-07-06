package com.jamsirat.atmapi.service.impl;


import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.product.RequestDetailProductDto;
import com.jamsirat.atmapi.dto.request.product.RequestUpdateProductDto;
import com.jamsirat.atmapi.dto.response.product.ResponseDetailProductDto;
import com.jamsirat.atmapi.dto.response.product.ResponseUpdateProductDto;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.model.Product;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    @Override
    public HttpResponse<Object> getListProduct() {
        List<Product> product = productRepository.findAll();
        List<ResponseDetailProductDto> listproduct =   product.stream()
                .map(product1 -> {
                    ResponseDetailProductDto dto = MapperUtil.parse(product1, ResponseDetailProductDto.class,MatchingStrategies.STRICT);
                    return dto;
                }).collect(Collectors.toList());

        return  HttpResponse.buildHttpResponse("Data fetched Successfully",
                "Data Fetched",
                HttpStatus.OK,
                HttpStatus.OK.value(),
                listproduct);

    }

    @Override
    public HttpResponse<Object> updateProduct(RequestUpdateProductDto request) {
        var product = productRepository.findById(request.getId()).orElseThrow(() ->  new DataNotFoundException(String.format("Product with id %d is not exist", request.getId()),"please check again your Product id"));
        var categoryProduct = categoryRepository.findByProduct(request.getProductCategoryId()).orElseThrow(() -> new DataNotFoundException(String.format("category  with product id %d is not exist", product.getId()),"please check again your Product id"));
        product.setProductName(request.getProductName());
        product.setDescriptionProduct(request.getDescriptionProduct());
        product.setStockProduct(request.getStockProduct());
        product.setImage(request.getImage());
        product.setProductCategory(categoryProduct);
        productRepository.save(product);
        ResponseUpdateProductDto responseData =  MapperUtil.parse(product, ResponseUpdateProductDto.class, MatchingStrategies.STRICT);
        return HttpResponse.buildHttpResponse("Product has been updated",
                "Data saved",
                 HttpStatus.OK,
                 HttpStatus.OK.value(),
                 responseData);

        }

    @Override
    public HttpResponse<Object> deleteProduct(Long productId) {
        var product = productRepository.findById(productId).orElseThrow(() -> new DataNotFoundException(String.format("Product with id %d is not exist", productId),"please check again your Product id"));
        product.setIsDeleted(true);
        productRepository.save(product);
        return HttpResponse.buildHttpResponse("Product has been deleted",
                "Data deleted",
                HttpStatus.OK,
                HttpStatus.OK.value(),
                null);
    }

    @Override
    public HttpResponse<Object> getDetailProduct(RequestDetailProductDto request) {
            Product product = productRepository.findById(request.getId()).orElseThrow(() -> new DataNotFoundException(String.format("Product with id %d is not exist", request.getId()),"please check again your Product id"));
            ResponseDetailProductDto data = MapperUtil.parse(product,ResponseDetailProductDto.class,MatchingStrategies.STRICT);
            return HttpResponse.buildHttpResponse("Detail product fetched success",
                    "Data fetched",
                    HttpStatus.OK,
                    HttpStatus.OK.value(),
                    data);

    }

}
