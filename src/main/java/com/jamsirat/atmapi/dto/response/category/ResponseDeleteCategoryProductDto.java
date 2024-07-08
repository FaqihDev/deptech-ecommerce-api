package com.jamsirat.atmapi.dto.response.category;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDeleteCategoryProductDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String message;
}


