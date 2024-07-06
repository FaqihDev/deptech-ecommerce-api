package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.product.RequestDetailProductDto;
import com.jamsirat.atmapi.dto.request.product.RequestUpdateProductDto;

public interface IProductService {

    HttpResponse<Object> getListProduct();

    HttpResponse<Object> updateProduct(RequestUpdateProductDto request);

    HttpResponse<Object> deleteProduct(Long ProductId);

    HttpResponse<Object> getDetailProduct(RequestDetailProductDto request);
}
