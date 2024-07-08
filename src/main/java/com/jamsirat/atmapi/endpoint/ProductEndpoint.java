package com.jamsirat.atmapi.endpoint;


import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.product.RequestAddProductDto;
import com.jamsirat.atmapi.dto.request.product.RequestUpdateProductDto;
import com.jamsirat.atmapi.dto.response.product.ResponseDetailProductDto;
import com.jamsirat.atmapi.service.IProductService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.PRODUCT,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductEndpoint {

    private final IProductService productService;

    @PostMapping(IApplicationConstant.Path.Product.ADD_PRODUCT)
    public ResponseEntity<?> addProduct(@RequestBody RequestAddProductDto request) {
        try {
            HttpResponse<?> response = productService.addProduct(request);
            if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @GetMapping(IApplicationConstant.Path.Product.LIST_PRODUCT)
    public ResponseEntity<?> fetchProducts() {
        try {
            HttpResponse<List<ResponseDetailProductDto>> response = productService.getListProduct();
            if (Objects.nonNull(response) && Objects.nonNull(response.getData()) && !response.getData().isEmpty()) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PutMapping(IApplicationConstant.Path.Product.UPDATE_PRODUCT)
    public ResponseEntity<?> updateProduct(@RequestBody RequestUpdateProductDto request) {
        try {
            HttpResponse<?> response = productService.updateProduct(request);
            if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @DeleteMapping(IApplicationConstant.Path.Product.DELETE_PRODUCT)
    public ResponseEntity<?> delete(@PathVariable Long productId) {
        try {
            HttpResponse<?> response = productService.deleteProduct(productId);
            if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @GetMapping(IApplicationConstant.Path.Product.PRODUCT_DETAIL)
    public ResponseEntity<?> detailProduct(@PathVariable Long productId) {
        try {
            HttpResponse<Object> detailProduct = productService.getDetailProduct(productId);
            if (Objects.nonNull(detailProduct) && Objects.nonNull(detailProduct.getData())) {
                return ResponseEntity.ok(detailProduct);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }
}

