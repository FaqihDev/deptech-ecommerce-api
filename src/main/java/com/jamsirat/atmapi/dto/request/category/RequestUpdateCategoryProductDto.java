package com.jamsirat.atmapi.dto.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateCategoryProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String categoryName;
    private String descriptionCategory;
}
