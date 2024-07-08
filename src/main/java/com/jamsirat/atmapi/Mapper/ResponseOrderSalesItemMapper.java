package com.jamsirat.atmapi.Mapper;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.order.ResponseOrderSalesItemDto;
import com.jamsirat.atmapi.model.order.OrderSalesItem;
import org.springframework.stereotype.Component;


@Component
public class ResponseOrderSalesItemMapper extends ADATAMapper<OrderSalesItem, ResponseOrderSalesItemDto> {
    @Override
    public ResponseOrderSalesItemDto convert(OrderSalesItem request) {
             return ResponseOrderSalesItemDto.builder()
                        .orderSalesItemId(request.getId())
                        .productName(request.getProductName())
                        .categoryProduct(request.getCategoryName())
                        .price(request.getPrice())
                        .quantity(request.getQuantity())
                        .build();

    }

}
