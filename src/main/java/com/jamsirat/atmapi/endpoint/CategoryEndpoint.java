package com.jamsirat.atmapi.endpoint;
import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.category.RequestAddCategoryProductDto;
import com.jamsirat.atmapi.dto.request.category.RequestUpdateCategoryProductDto;
import com.jamsirat.atmapi.dto.response.category.ResponseDetailCategoryProductDto;
import com.jamsirat.atmapi.service.ICategoryProductService;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = IApplicationConstant.ContextPath.CATEGORY_PRODUCT,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryEndpoint {

    private final ICategoryProductService categoryProductService;

    @PostMapping(IApplicationConstant.Path.CategoryProduct.ADD_CATEGORY)
    public ResponseEntity<?> addCategory(@RequestBody RequestAddCategoryProductDto request) {
        HttpResponse<?> response = categoryProductService.addCategoryProduct(request);
        return HttpResponse.okOrNoContent(response);
    }

    @GetMapping(IApplicationConstant.Path.CategoryProduct.LIST_CATEGORY_PRODUCT)
    public ResponseEntity<?> fetchCategory() {
        HttpResponse<List<ResponseDetailCategoryProductDto>> response = categoryProductService.getListCategoryProduct();
        return HttpResponse.okOrNoContent(response);
    }

    @GetMapping(IApplicationConstant.Path.CategoryProduct.CATEGORY_PRODUCT_DETAIL)
    public ResponseEntity<?> detailCategory(@PathVariable Long categoryId) {
        HttpResponse<ResponseDetailCategoryProductDto> response = categoryProductService.getDetailCategoryProduct(categoryId);
        return HttpResponse.okOrNoContent(response);
    }

    @PutMapping(IApplicationConstant.Path.CategoryProduct.UPDATE_CATEGORY_PRODUCT)
    public ResponseEntity<?> updateCategory(@RequestBody RequestUpdateCategoryProductDto request) {
        HttpResponse<?> response = categoryProductService.updateCategoryProduct(request);
        return HttpResponse.okOrNoContent(response);
    }

    @DeleteMapping(IApplicationConstant.Path.CategoryProduct.DELETE_CATEGORY_PRODUCT)
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        HttpResponse<?> response = categoryProductService.deleteCategoryProduct(categoryId);
        return ResponseEntity.ok(response);
    }

}


