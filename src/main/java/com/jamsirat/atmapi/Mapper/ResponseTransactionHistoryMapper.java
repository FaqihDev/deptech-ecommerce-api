package com.jamsirat.atmapi.Mapper;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.order.ResponseTransactionHistoryDto;
import com.jamsirat.atmapi.model.order.OrderSales;

import com.jamsirat.atmapi.repository.IOrderSalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ResponseTransactionHistoryMapper extends ADATAMapper<OrderSales, ResponseTransactionHistoryDto> {

    private final ResponseOrderSalesItemMapper orderSalesItemMapper;
    private final IOrderSalesItemRepository orderSalesItemRepository;

    @Override
    public ResponseTransactionHistoryDto convert(OrderSales orderSales) {

        var orderSalesItems = orderSalesItemRepository.findByTransactionId(orderSales.getId());
        return ResponseTransactionHistoryDto.builder()
                .transactionTime(orderSales.getCreatedAt())
                .orderSalesItems(orderSalesItemMapper.entitiesIntoDTOs(orderSalesItems))
                .transactionId(orderSales.getPublicId())
                .type(orderSales.getTransactionType())
                .userId(orderSales.getUser().getId())
                .build();
    }
}
