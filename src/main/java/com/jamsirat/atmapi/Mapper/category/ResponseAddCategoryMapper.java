package com.jamsirat.atmapi.Mapper.category;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.category.ResponseAddCategoryProductDto;
import com.jamsirat.atmapi.model.inventory.CategoryProduct;
import org.springframework.stereotype.Service;


@Service
public class ResponseAddCategoryMapper extends ADATAMapper<CategoryProduct, ResponseAddCategoryProductDto> {
    @Override
    public ResponseAddCategoryProductDto convert(CategoryProduct categoryProduct) {
       return ResponseAddCategoryProductDto.builder()
                .id(categoryProduct.getId())
                .categoryName(categoryProduct.getCategoryName())
                .descriptionCategory(categoryProduct.getDescriptionCategory())
               .build();
    }
}
