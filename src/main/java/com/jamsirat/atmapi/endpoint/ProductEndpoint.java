package com.jamsirat.atmapi.endpoint;


import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.product.RequestAddProductDto;
import com.jamsirat.atmapi.dto.request.product.RequestUpdateProductDto;
import com.jamsirat.atmapi.dto.response.product.ResponseDetailProductDto;
import com.jamsirat.atmapi.service.IProductService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.PRODUCT,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductEndpoint {

    private final IProductService productService;

    @PostMapping(IApplicationConstant.Path.Product.ADD_PRODUCT)
    public ResponseEntity<?> addProduct(@RequestBody RequestAddProductDto request) {
        HttpResponse<?> response = productService.addProduct(request);
        return HttpResponse.okOrNoContent(response);
    }

    @GetMapping(IApplicationConstant.Path.Product.LIST_PRODUCT)
    public ResponseEntity<?> fetchProducts() {
        HttpResponse<List<ResponseDetailProductDto>> response = productService.getListProduct();
        return HttpResponse.okOrNoContent(response);
    }

    @PutMapping(IApplicationConstant.Path.Product.UPDATE_PRODUCT)
    public ResponseEntity<?> updateProduct(@RequestBody RequestUpdateProductDto request) {
        HttpResponse<?> response = productService.updateProduct(request);
        return HttpResponse.okOrNoContent(response);
    }

    @DeleteMapping(IApplicationConstant.Path.Product.DELETE_PRODUCT)
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        HttpResponse<?> response = productService.deleteProduct(productId);
        return HttpResponse.okOrNoContent(response);
    }

    @GetMapping(IApplicationConstant.Path.Product.PRODUCT_DETAIL)
    public ResponseEntity<?> detailProduct(@PathVariable Long productId) {
        HttpResponse<Object> response = productService.getDetailProduct(productId);
        return ResponseEntity.ok(response);
    }
}

