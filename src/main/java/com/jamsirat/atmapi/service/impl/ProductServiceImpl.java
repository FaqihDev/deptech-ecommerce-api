package com.jamsirat.atmapi.service.impl;


import com.jamsirat.atmapi.Mapper.product.ResponseAddProductMapper;
import com.jamsirat.atmapi.Mapper.product.ResponseDetailProductMapper;
import com.jamsirat.atmapi.Mapper.product.ResponseUpdateProductMapper;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.SuccessMessage;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperSuccessMessage;



import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final ResponseAddProductMapper responseAddProductMapper;
    private final ResponseDetailProductMapper responseDetailProductMapper;
    private final ResponseUpdateProductMapper responseUpdateProductMapper;


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
        ResponseAddProductDto data = responseAddProductMapper.convert(product);
        return HttpResponse.build(DeveloperSuccessMessage.DATA_ADDED_SUCCESSFULLY,
                SuccessMessage.DATA_ADDED_SUCCESSFULLY,
                HttpStatus.CREATED,
                data);
    }

    @Override
    public HttpResponse<List<ResponseDetailProductDto>> getListProduct() {
        List<Product> products = productRepository.findAll().stream()
                .filter(x -> x.getProductCategory() != null)
                .toList();

        List<ResponseDetailProductDto> data = responseDetailProductMapper.entitiesIntoDTOs(products);
        return HttpResponse.build(DeveloperSuccessMessage.DATA_FETCH_SUCCESSFULLY,
                SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                HttpStatus.OK,
                data);

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
        ResponseUpdateProductDto responseData =  responseUpdateProductMapper.convert(product);
        return HttpResponse.build(DeveloperSuccessMessage.DATA_UPDATED_SUCCESSFULLY,
                SuccessMessage.DATA_UPDATED_SUCCESSFULLY,
                 HttpStatus.OK,
                 responseData);

        }

    @Override
    public HttpResponse<Object> deleteProduct(Long productId) {
        var product = productRepository.findById(productId).orElseThrow(() -> new DataNotFoundException(String.format("Product with id %d is not exist", productId),"please check again your Product id"));
        product.setIsDeleted(true);
        productRepository.save(product);
        return HttpResponse.build(DeveloperSuccessMessage.DATA_DELETED_SUCCESSFULLY,
                SuccessMessage.DATA_DELETED_SUCCESSFULLY,
                HttpStatus.OK,
                null);
    }

    @Override
    public HttpResponse<Object> getDetailProduct(Long productId) {
        var product = productRepository.findByIdAndProductCategoryIsNotNull(productId);
        if (product.isEmpty()) {
            return HttpResponse.noContent();
        }

        ResponseDetailProductDto data = responseDetailProductMapper.convert(product.get());
        return HttpResponse.build(SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                HttpStatus.OK,
                data);
    }

}
