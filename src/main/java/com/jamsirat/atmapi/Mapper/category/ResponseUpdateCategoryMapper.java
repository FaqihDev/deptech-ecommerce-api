package com.jamsirat.atmapi.Mapper.category;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.category.ResponseUpdateCategoryProductDto;
import com.jamsirat.atmapi.model.inventory.CategoryProduct;
import org.springframework.stereotype.Component;


@Component
public class ResponseUpdateCategoryMapper extends ADATAMapper<CategoryProduct, ResponseUpdateCategoryProductDto> {
    @Override
    public ResponseUpdateCategoryProductDto convert(CategoryProduct source) {
        return ResponseUpdateCategoryProductDto
                .builder()
                .id(source.getId())
                .categoryName(source.getCategoryName())
                .descriptionCategory(source.getDescriptionCategory())
                .build();
    }
}
