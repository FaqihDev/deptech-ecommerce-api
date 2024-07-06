package com.jamsirat.atmapi.dto.response.product;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDeleteProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
}

