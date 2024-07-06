package com.jamsirat.atmapi.dto.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateCategoryProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String categoryName;
    private String categoryProduct;
}