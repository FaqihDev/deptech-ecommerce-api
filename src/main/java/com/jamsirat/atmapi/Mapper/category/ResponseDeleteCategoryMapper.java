package com.jamsirat.atmapi.Mapper.category;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.category.ResponseDeleteCategoryProductDto;
import com.jamsirat.atmapi.model.inventory.CategoryProduct;
import org.springframework.stereotype.Component;

import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.SuccessMessage;


@Component
public class ResponseDeleteCategoryMapper extends ADATAMapper<CategoryProduct, ResponseDeleteCategoryProductDto> {
    @Override
    public ResponseDeleteCategoryProductDto convert(CategoryProduct categoryProduct) {
        return ResponseDeleteCategoryProductDto.builder()
                .id(categoryProduct.getId())
                .message(SuccessMessage.DATA_DELETED_SUCCESSFULLY)
                .build();
    }
}
