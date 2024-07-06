package com.jamsirat.atmapi.service.impl;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.category.RequestAddCategoryProductDto;
import com.jamsirat.atmapi.dto.request.category.RequestUpdateCategoryProductDto;
import com.jamsirat.atmapi.dto.response.category.ResponseAddCategoryProductDto;
import com.jamsirat.atmapi.dto.response.category.ResponseDetailCategoryProductDto;
import com.jamsirat.atmapi.dto.response.category.ResponseUpdateCategoryProductDto;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.model.CategoryProduct;
import com.jamsirat.atmapi.repository.ICategoryRepository;
import com.jamsirat.atmapi.service.ICategoryProductService;
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
public class CategoryProductServiceImpl implements ICategoryProductService {

    private final ICategoryRepository categoryRepository;


    @Override
    public HttpResponse<Object> addCategoryProduct(RequestAddCategoryProductDto request) {

        CategoryProduct categoryProduct =
                CategoryProduct.builder()
                        .categoryName(request.getCategoryName())
                        .descriptionCategory(request.getDescriptionCategory())
                        .build();

        categoryRepository.save(categoryProduct);
        ResponseAddCategoryProductDto data = MapperUtil.parse(categoryProduct,ResponseAddCategoryProductDto.class,MatchingStrategies.STRICT);
        return HttpResponse.buildHttpResponse("Data Added Successfully",
                "Data Saved",
                HttpStatus.CREATED,
                HttpStatus.CREATED.value(),
                data);

    }

    @Override
    public HttpResponse<Object> getListCategoryProduct() {
        List<CategoryProduct> CategoryProduct = categoryRepository.findAll();
        List<ResponseDetailCategoryProductDto> listCategoryProduct =   CategoryProduct.stream()
                .map(CategoryProduct1 -> {
                    ResponseDetailCategoryProductDto category = MapperUtil.parse(CategoryProduct1, ResponseDetailCategoryProductDto.class, MatchingStrategies.STRICT);
                    return category;
                }).collect(Collectors.toList());

        return  HttpResponse.buildHttpResponse("Data fetched Successfully",
                "Data Fetched",
                HttpStatus.OK,
                HttpStatus.OK.value(),
                listCategoryProduct);

    }

    @Override
    public HttpResponse<Object> updateCategoryProduct(RequestUpdateCategoryProductDto request) {
        var categoryProduct = categoryRepository.findById(request.getId()).orElseThrow(() ->  new DataNotFoundException(String.format("CategoryProduct with id %d is not exist", request.getId()),"please check again your CategoryProduct id"));
        categoryProduct.setCategoryName(request.getCategoryName());
        categoryProduct.setDescriptionCategory(request.getDescriptionCategory());
        ResponseUpdateCategoryProductDto responseData =  MapperUtil.parse(categoryProduct, ResponseUpdateCategoryProductDto.class, MatchingStrategies.STRICT);
        return HttpResponse.buildHttpResponse("CategoryProduct has been updated",
                "Data saved",
                HttpStatus.OK,
                HttpStatus.OK.value(),
                responseData);

    }

    @Override
    public HttpResponse<Object> deleteCategoryProduct(Long CategoryProductId) {
        var CategoryProduct = categoryRepository.findById(CategoryProductId).orElseThrow(() -> new DataNotFoundException(String.format("CategoryProduct with id %d is not exist", CategoryProductId),"please check again your CategoryProduct id"));
        CategoryProduct.setIsDeleted(true);
        categoryRepository.save(CategoryProduct);
        return HttpResponse.buildHttpResponse("CategoryProduct has been deleted",
                "Data deleted",
                HttpStatus.OK,
                HttpStatus.OK.value(),
                null);
    }

    @Override
    public HttpResponse<Object> getDetailCategoryProduct(Long categoryId) {
        CategoryProduct CategoryProduct = categoryRepository.findById(categoryId).orElseThrow(() -> new DataNotFoundException(String.format("CategoryProduct with id %d is not exist", categoryId), "please check again your CategoryProduct id"));
        ResponseDetailCategoryProductDto data = MapperUtil.parse(CategoryProduct, ResponseDetailCategoryProductDto.class, MatchingStrategies.STRICT);
        return HttpResponse.buildHttpResponse("Detail CategoryProduct fetched success",
                "Data fetched",
                HttpStatus.OK,
                HttpStatus.OK.value(),
                data);

    }
}
