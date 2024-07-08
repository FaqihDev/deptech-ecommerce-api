package com.jamsirat.atmapi.dto.response.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderSalesItemDto {

    private Long orderSalesItemId;
    private String productName;
    private String categoryProduct;
    private Integer quantity;
    private BigDecimal price;
}
