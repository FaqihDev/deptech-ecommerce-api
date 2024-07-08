package com.jamsirat.atmapi.Mapper.product;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.product.ResponseAddProductDto;
import com.jamsirat.atmapi.model.inventory.Product;
import org.springframework.stereotype.Component;


@Component
public class ResponseAddProductMapper extends ADATAMapper<Product, ResponseAddProductDto> {
    @Override
    public ResponseAddProductDto convert(Product source) {
        return ResponseAddProductDto
                .builder()
                .id(source.getId())
                .productCategoryName(source.getProductCategory().getCategoryName())
                .image(source.getImage())
                .descriptionProduct(source.getDescriptionProduct())
                .stockProduct(source.getStockProduct())
                .build();
    }
}
