package com.jamsirat.atmapi.Mapper.category;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.category.ResponseDetailCategoryProductDto;
import com.jamsirat.atmapi.model.inventory.CategoryProduct;
import org.springframework.stereotype.Component;


@Component
public class ResponseDetailCategoryMapper extends ADATAMapper<CategoryProduct,ResponseDetailCategoryProductDto > {
    @Override
    public ResponseDetailCategoryProductDto convert(CategoryProduct source) {
        return ResponseDetailCategoryProductDto.builder()
                .categoryName(source.getCategoryName())
                .id(source.getId())
                .descriptionCategory(source.getDescriptionCategory())
                .build();
    }
}
