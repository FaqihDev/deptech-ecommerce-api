package com.jamsirat.atmapi.endpoint;

import com.jamsirat.atmapi.dto.request.category.RequestAddCategoryProductDto;
import com.jamsirat.atmapi.dto.request.product.RequestAddProductDto;
import com.jamsirat.atmapi.dto.request.product.RequestDetailProductDto;
import com.jamsirat.atmapi.dto.request.product.RequestUpdateProductDto;
import com.jamsirat.atmapi.service.IProductService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.PRODUCT,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductEndpoint {

    private final IProductService productService;

    @PostMapping(IApplicationConstant.Path.Product.ADD_PRODUCT)
    public ResponseEntity<?> addProduct(@RequestBody RequestAddProductDto request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @GetMapping(IApplicationConstant.Path.Product.LIST_PRODUCT)
    public ResponseEntity<?> fetchProducts() {
        return ResponseEntity.ok(productService.getListProduct());
    }

    @GetMapping(IApplicationConstant.Path.Product.PRODUCT_DETAIL)
    public ResponseEntity<?> detailProduct(Long productId) {
        return ResponseEntity.ok(productService.getDetailProduct(productId));
    }

    @PutMapping(IApplicationConstant.Path.Product.UPDATE_PRODUCT)
    public ResponseEntity<?> update(@RequestBody RequestUpdateProductDto request) {
        return ResponseEntity.ok(productService.updateProduct(request));
    }

    @DeleteMapping(IApplicationConstant.Path.Product.DELETE_PRODUCT)
    public ResponseEntity<?> update(@PathVariable Long ProductId) {
        return ResponseEntity.ok(productService.deleteProduct(ProductId));
    }

}

