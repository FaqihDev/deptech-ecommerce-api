package com.jamsirat.atmapi.dto.response.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAddCategoryProductDto {

    private Long id;
    private String categoryName;
    private String descriptionCategory;
}
