package com.jamsirat.atmapi.dto.response.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAddCategoryProductDto {

    private String id;
    private String categoryName;
    private String descriptionCategory;
}
