package com.jamsirat.atmapi.Mapper.product;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.product.ResponseAddProductDto;
import com.jamsirat.atmapi.dto.response.product.ResponseUpdateProductDto;
import com.jamsirat.atmapi.model.inventory.Product;
import org.springframework.stereotype.Component;


@Component
public class ResponseUpdateProductMapper extends ADATAMapper<Product, ResponseUpdateProductDto> {
    @Override
    public ResponseUpdateProductDto convert(Product source) {
        return ResponseUpdateProductDto
                .builder()
                .id(source.getId())
                .image(source.getImage())
                .descriptionProduct(source.getDescriptionProduct())
                .stockProduct(source.getStockProduct())
                .build();
    }
}
