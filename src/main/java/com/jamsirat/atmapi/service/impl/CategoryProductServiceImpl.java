package com.jamsirat.atmapi.service.impl;

import com.jamsirat.atmapi.Mapper.category.ResponseAddCategoryMapper;
import com.jamsirat.atmapi.Mapper.category.ResponseDetailCategoryMapper;
import com.jamsirat.atmapi.Mapper.category.ResponseUpdateCategoryMapper;
import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.category.RequestAddCategoryProductDto;
import com.jamsirat.atmapi.dto.request.category.RequestUpdateCategoryProductDto;
import com.jamsirat.atmapi.dto.response.category.ResponseAddCategoryProductDto;
import com.jamsirat.atmapi.dto.response.category.ResponseDetailCategoryProductDto;
import com.jamsirat.atmapi.dto.response.category.ResponseUpdateCategoryProductDto;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.model.inventory.CategoryProduct;
import com.jamsirat.atmapi.repository.ICategoryRepository;
import com.jamsirat.atmapi.repository.IProductRepository;
import com.jamsirat.atmapi.service.ICategoryProductService;
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
public class CategoryProductServiceImpl implements ICategoryProductService {

    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;
    private final ResponseAddCategoryMapper responseAddCategoryMapper;
    private final ResponseDetailCategoryMapper responseDetailCategoryMapper;
    private final ResponseUpdateCategoryMapper responseUpdateCategoryMapper;



    @Override
    public HttpResponse<Object> addCategoryProduct(RequestAddCategoryProductDto request) {
        CategoryProduct categoryProduct =
                CategoryProduct.builder()
                        .categoryName(request.getCategoryName())
                        .descriptionCategory(request.getDescriptionCategory())
                        .build();

        categoryRepository.save(categoryProduct);
        ResponseAddCategoryProductDto data = responseAddCategoryMapper.convert(categoryProduct);
        return HttpResponse.build(SuccessMessage.DATA_ADDED_SUCCESSFULLY,
                DeveloperSuccessMessage.DATA_ADDED_SUCCESSFULLY,
                HttpStatus.CREATED,
                data);

    }

    @Override
    public HttpResponse<List<ResponseDetailCategoryProductDto>> getListCategoryProduct() {
        List<CategoryProduct> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return HttpResponse.noContent();
        }

        List<ResponseDetailCategoryProductDto> listsData = responseDetailCategoryMapper.entitiesIntoDTOs(categories);
        return HttpResponse.build(DeveloperSuccessMessage.DATA_FETCH_SUCCESSFULLY,
                SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                HttpStatus.OK,
                listsData);
    }

    @Override
    public HttpResponse<Object> updateCategoryProduct(RequestUpdateCategoryProductDto request) {
        var categoryProduct = categoryRepository.findById(request.getId()).orElseThrow(() ->  new DataNotFoundException(String.format("CategoryProduct with id %d is not exist", request.getId()),"please check again your CategoryProduct id"));
        categoryProduct.setCategoryName(request.getCategoryName());
        categoryProduct.setDescriptionCategory(request.getDescriptionCategory());
        ResponseUpdateCategoryProductDto data =  responseUpdateCategoryMapper.convert(categoryProduct);

        return HttpResponse.build(DeveloperSuccessMessage.DATA_UPDATED_SUCCESSFULLY,
                SuccessMessage.DATA_UPDATED_SUCCESSFULLY,
                HttpStatus.OK,
                data);

    }

    @Override
    public HttpResponse<Object> deleteCategoryProduct(Long categoryId) {
        var categoryProduct = categoryRepository.findById(categoryId).orElseThrow(() -> new DataNotFoundException(String.format("CategoryProduct with id %d is not exist", categoryId),"please check again your CategoryProduct id"));
        var productsByCategories = productRepository.findByProductCategory(categoryProduct);
        productsByCategories.forEach(product -> product.setIsDeleted(true));
        categoryProduct.setIsDeleted(true);
        categoryRepository.save(categoryProduct);
        return HttpResponse.build(SuccessMessage.DATA_DELETED_SUCCESSFULLY,
                DeveloperSuccessMessage.DATA_DELETED_SUCCESSFULLY,
                HttpStatus.OK,
                null);
    }

    @Override
    public HttpResponse<ResponseDetailCategoryProductDto> getDetailCategoryProduct(Long categoryId) {
        var categoryProductOptional = categoryRepository.findById(categoryId);
        if (categoryProductOptional.isPresent()) {
            CategoryProduct categoryProduct = categoryProductOptional.get();

            ResponseDetailCategoryProductDto data = responseDetailCategoryMapper.convert(categoryProduct);
            return HttpResponse.build(DeveloperSuccessMessage.DATA_DELETED_SUCCESSFULLY,
                    SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                    HttpStatus.OK,
                    data);
        }
        return HttpResponse.noContent();
    }

}
