package com.jamsirat.atmapi.dto.response.order;

import com.jamsirat.atmapi.statval.enumeration.ETransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTransactionDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String transactionId;
    private List<ResponseTransactionProductDto> transactionProducts;
    private ETransactionType type;
    private BigDecimal totalPrice;
    private LocalDateTime transactionTime;
}

