package com.jamsirat.atmapi.dto.request.product;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDeleteProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
}

