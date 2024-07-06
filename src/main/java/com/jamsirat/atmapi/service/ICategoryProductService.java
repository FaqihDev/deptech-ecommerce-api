package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.category.RequestAddCategoryProductDto;
import com.jamsirat.atmapi.dto.request.category.RequestUpdateCategoryProductDto;
import com.jamsirat.atmapi.dto.request.product.RequestAddProductDto;


public interface ICategoryProductService {
    
    HttpResponse<Object> addCategoryProduct(RequestAddCategoryProductDto request);

    HttpResponse<Object> getListCategoryProduct();

    HttpResponse<Object> updateCategoryProduct(RequestUpdateCategoryProductDto request);

    HttpResponse<Object> deleteCategoryProduct(Long categoryProductId);

    HttpResponse<Object> getDetailCategoryProduct(Long categoryProductId);
  
}
