package com.jamsirat.atmapi.Mapper.order;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.order.ResponseTransactionProductDto;
import com.jamsirat.atmapi.model.order.OrderSalesItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ResponseTransactionProductMapper extends ADATAMapper<OrderSalesItem, ResponseTransactionProductDto> {

    @Override
    public ResponseTransactionProductDto convert(OrderSalesItem request) {
        return ResponseTransactionProductDto.builder()
                .productName(request.getProductName())
                .categoryName(request.getCategoryName())
                .quantity(request.getQuantity())
                .build();
    }


}




