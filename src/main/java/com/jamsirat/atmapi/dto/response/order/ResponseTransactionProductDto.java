package com.jamsirat.atmapi.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTransactionProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String productName;
    private String categoryName;
    private Integer quantity;
}
