package com.jamsirat.atmapi.dto.response.product;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCreateProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String productName;
    private String descriptionProduct;
    private String image;
    private String productCategoryName;
    private Integer stockProduct;
}

