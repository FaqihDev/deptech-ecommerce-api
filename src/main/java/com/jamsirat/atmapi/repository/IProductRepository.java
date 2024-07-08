package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.inventory.CategoryProduct;
import com.jamsirat.atmapi.model.inventory.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p JOIN p.orderSalesItems osi WHERE osi.id IN :orderSalesItemIds GROUP BY p")
    List<Product> findAllByOrderSalesItemIds(@Param("orderSalesItemIds") List<Long> orderSalesItemIds);

    List<Product> findByProductCategory(CategoryProduct categoryProduct);

    Optional<Product> findByIdAndProductCategoryIsNotNull(Long productId);



}
