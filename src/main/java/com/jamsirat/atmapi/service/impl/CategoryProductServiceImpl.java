package com.jamsirat.atmapi.service.impl;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.category.RequestAddCategoryProductDto;
import com.jamsirat.atmapi.dto.request.category.RequestUpdateCategoryProductDto;
import com.jamsirat.atmapi.dto.response.category.ResponseAddCategoryProductDto;
import com.jamsirat.atmapi.dto.response.category.ResponseDetailCategoryProductDto;
import com.jamsirat.atmapi.dto.response.category.ResponseUpdateCategoryProductDto;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.model.inventory.CategoryProduct;
import com.jamsirat.atmapi.repository.ICategoryRepository;
import com.jamsirat.atmapi.service.ICategoryProductService;
import com.jamsirat.atmapi.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.SuccessMessage;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.ExceptionMessage;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperSuccessMessage;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperExceptionMessage;

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
        return HttpResponse.buildHttpResponse(SuccessMessage.DATA_ADDED_SUCCESSFULLY,
                DeveloperSuccessMessage.DATA_ADDED_SUCCESSFULLY,
                HttpStatus.CREATED,
                HttpStatus.CREATED.value(),
                data);

    }

    @Override
    public HttpResponse<List<ResponseDetailCategoryProductDto>> getListCategoryProduct() {
        List<CategoryProduct> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return HttpResponse.noContent();
        }
        List<ResponseDetailCategoryProductDto> data = categories.stream()
                .map(category -> MapperUtil.parse(category, ResponseDetailCategoryProductDto.class, MatchingStrategies.STRICT))
                .collect(Collectors.toList());
        return HttpResponse.buildHttpResponse(DeveloperSuccessMessage.DATA_FETCH_SUCCESSFULLY,
                SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                HttpStatus.OK,
                HttpStatus.OK.value(),
                data);
    }

    @Override
    public HttpResponse<Object> updateCategoryProduct(RequestUpdateCategoryProductDto request) {
        var categoryProduct = categoryRepository.findById(request.getId()).orElseThrow(() ->  new DataNotFoundException(String.format("CategoryProduct with id %d is not exist", request.getId()),"please check again your CategoryProduct id"));
        categoryProduct.setCategoryName(request.getCategoryName());
        categoryProduct.setDescriptionCategory(request.getDescriptionCategory());
        ResponseUpdateCategoryProductDto responseData =  MapperUtil.parse(categoryProduct, ResponseUpdateCategoryProductDto.class, MatchingStrategies.STRICT);
        return HttpResponse.buildHttpResponse(DeveloperSuccessMessage.DATA_UPDATED_SUCCESSFULLY,
                SuccessMessage.DATA_UPDATED_SUCCESSFULLY,
                HttpStatus.OK,
                HttpStatus.OK.value(),
                responseData);

    }

    @Override
    public HttpResponse<Object> deleteCategoryProduct(Long categoryId) {
        var CategoryProduct = categoryRepository.findById(categoryId).orElseThrow(() -> new DataNotFoundException(String.format("CategoryProduct with id %d is not exist", categoryId),"please check again your CategoryProduct id"));
        CategoryProduct.setIsDeleted(true);
        categoryRepository.save(CategoryProduct);
        return HttpResponse.buildHttpResponse(SuccessMessage.DATA_DELETED_SUCCESSFULLY,
                DeveloperSuccessMessage.DATA_DELETED_SUCCESSFULLY,
                HttpStatus.OK,
                HttpStatus.OK.value(),
                null);
    }

    @Override
    public HttpResponse<ResponseDetailCategoryProductDto> getDetailCategoryProduct(Long categoryId) {
        var categoryProductOptional = categoryRepository.findById(categoryId);
        if (categoryProductOptional.isPresent()) {
            CategoryProduct categoryProduct = categoryProductOptional.get();
            ResponseDetailCategoryProductDto data = MapperUtil.parse(categoryProduct, ResponseDetailCategoryProductDto.class, MatchingStrategies.STRICT);
            return HttpResponse.buildHttpResponse(DeveloperSuccessMessage.DATA_DELETED_SUCCESSFULLY,
                    SuccessMessage.DATA_FETCH_SUCCESSFULLY,
                    HttpStatus.OK,
                    HttpStatus.OK.value(),
                    data);
        }
        return HttpResponse.noContent();
    }

}
