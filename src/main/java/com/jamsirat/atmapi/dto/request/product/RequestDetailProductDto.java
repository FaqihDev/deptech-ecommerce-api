package com.jamsirat.atmapi.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetailProductDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String productName;
    private String descriptionProduct;
    private String image;
    private String productCategoryName;
    private Integer stockProduct;
}
