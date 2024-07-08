package com.jamsirat.atmapi.endpoint;
import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.dto.request.category.RequestAddCategoryProductDto;
import com.jamsirat.atmapi.dto.request.category.RequestUpdateCategoryProductDto;
import com.jamsirat.atmapi.dto.response.category.ResponseDetailCategoryProductDto;
import com.jamsirat.atmapi.service.ICategoryProductService;
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
@RequestMapping(value = IApplicationConstant.ContextPath.CATEGORY_PRODUCT,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryEndpoint {

    private final ICategoryProductService categoryProductService;

    @PostMapping(IApplicationConstant.Path.CategoryProduct.ADD_CATEGORY)
    public ResponseEntity<?> addCategory(@RequestBody RequestAddCategoryProductDto request) {
        try {
            HttpResponse<?> response = categoryProductService.addCategoryProduct(request);
            if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @GetMapping(IApplicationConstant.Path.CategoryProduct.LIST_CATEGORY_PRODUCT)
    public ResponseEntity<?> fetchCategory() {
        try {
            HttpResponse<List<ResponseDetailCategoryProductDto>> response = categoryProductService.getListCategoryProduct();
            if (Objects.nonNull(response) && Objects.nonNull(response.getData()) && !response.getData().isEmpty()) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @GetMapping(IApplicationConstant.Path.CategoryProduct.CATEGORY_PRODUCT_DETAIL)
    public ResponseEntity<?> detailCategory(@PathVariable Long categoryId) {
        try {
            HttpResponse<ResponseDetailCategoryProductDto> response = categoryProductService.getDetailCategoryProduct(categoryId);
            if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PutMapping(IApplicationConstant.Path.CategoryProduct.UPDATE_CATEGORY_PRODUCT)
    public ResponseEntity<?> updateCategory(@RequestBody RequestUpdateCategoryProductDto request) {
        try {
            HttpResponse<?> response = categoryProductService.updateCategoryProduct(request);
            if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @DeleteMapping(IApplicationConstant.Path.CategoryProduct.DELETE_CATEGORY_PRODUCT)
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        try {
            HttpResponse<?> response = categoryProductService.deleteCategoryProduct(categoryId);
            if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(HttpResponse.noContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.buildHttpResponse("An error occurred", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

}


