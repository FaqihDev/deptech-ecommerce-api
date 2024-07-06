package com.jamsirat.atmapi.endpoint;
import com.jamsirat.atmapi.dto.request.category.RequestAddCategoryProductDto;
import com.jamsirat.atmapi.dto.request.category.RequestUpdateCategoryProductDto;
import com.jamsirat.atmapi.service.ICategoryProductService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.CATEGORY_PRODUCT,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryEndpoint {

    private final ICategoryProductService categoryProductService;

    @PostMapping(IApplicationConstant.Path.CategoryProduct.ADD_CATEGORY)
    public ResponseEntity<?> addCategory(@RequestBody RequestAddCategoryProductDto request) {
        return ResponseEntity.ok(categoryProductService.addCategoryProduct(request));
    }

    @GetMapping(IApplicationConstant.Path.CategoryProduct.LIST_CATEGORY_PRODUCT)
    public ResponseEntity<?> fetchCategory() {
        return ResponseEntity.ok(categoryProductService.getListCategoryProduct());
    }

    @GetMapping(IApplicationConstant.Path.CategoryProduct.CATEGORY_PRODUCT_DETAIL)
    public ResponseEntity<?> detailCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryProductService.getDetailCategoryProduct(categoryId));
    }

    @PutMapping(IApplicationConstant.Path.CategoryProduct.UPDATE_CATEGORY_PRODUCT)
    public ResponseEntity<?> updateCategory(@RequestBody RequestUpdateCategoryProductDto request) {
        return ResponseEntity.ok(categoryProductService.updateCategoryProduct(request));
    }

    @DeleteMapping(IApplicationConstant.Path.CategoryProduct.DELETE_CATEGORY_PRODUCT)
    public ResponseEntity<?> deleteCategory(@PathVariable Long ProductId) {
        return ResponseEntity.ok(categoryProductService.deleteCategoryProduct(ProductId));
    }

}


