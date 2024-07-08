package com.jamsirat.atmapi.Mapper.product;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.product.ResponseDetailProductDto;
import com.jamsirat.atmapi.model.inventory.CategoryProduct;
import com.jamsirat.atmapi.model.inventory.Product;
import com.jamsirat.atmapi.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class ResponseDetailProductMapper extends ADATAMapper<Product, ResponseDetailProductDto> {

    private final ICategoryRepository categoryRepository;

    @Override
    public ResponseDetailProductDto convert(Product source) {

           return   ResponseDetailProductDto
                    .builder()
                    .id(source.getId())
                    .productName(source.getProductName())
                    .categoryName(Objects.nonNull(source.getProductCategory()) ? source.getProductCategory().getCategoryName() : null)
                    .image(source.getImage())
                    .descriptionProduct(source.getDescriptionProduct())
                    .stockProduct(source.getStockProduct())
                    .build();

        }


}
