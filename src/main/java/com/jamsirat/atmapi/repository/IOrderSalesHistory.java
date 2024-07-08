package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.order.OrderSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderSalesHistory extends JpaRepository<OrderSales, Long> {
}
