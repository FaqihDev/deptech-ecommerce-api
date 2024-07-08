package com.jamsirat.atmapi.Mapper.product;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.product.ResponseAddProductDto;
import com.jamsirat.atmapi.dto.response.product.ResponseDeleteProductDto;
import com.jamsirat.atmapi.model.inventory.Product;
import org.springframework.stereotype.Component;


@Component
public class ResponseDeleteProductMapper extends ADATAMapper<Product, ResponseDeleteProductDto> {
    @Override
    public ResponseDeleteProductDto convert(Product source) {
        return ResponseDeleteProductDto
                .builder()
                .id(source.getId())
                .build();
    }
}
