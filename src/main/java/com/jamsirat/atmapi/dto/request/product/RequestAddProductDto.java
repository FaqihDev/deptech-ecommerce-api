package com.jamsirat.atmapi.dto.request.product;


import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 697096363431663222L;

    private String productName;
    private String image;
    private Long stockProduct;
    private String descriptionProduct;
    private Long productCategoryId;
}
