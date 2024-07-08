package com.jamsirat.atmapi.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestProductDto {
    private Long productId;
    private int quantity;
}
