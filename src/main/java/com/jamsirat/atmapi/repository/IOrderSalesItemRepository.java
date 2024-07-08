package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.order.OrderSalesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderSalesItemRepository extends JpaRepository<OrderSalesItem,Long> {

    @Query("SELECT osi FROM OrderSalesItem osi WHERE osi.orderSales.id = :orderSalesId")
    List<OrderSalesItem> findByTransactionId(Long orderSalesId);
}
