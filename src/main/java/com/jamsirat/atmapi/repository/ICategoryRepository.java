package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.inventory.CategoryProduct;
import com.jamsirat.atmapi.model.inventory.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryProduct,Long> {

    Optional<CategoryProduct> findByProducts(Product product);

    @Query("SELECT cp FROM CategoryProduct cp JOIN cp.products p WHERE p IN :products")
    List<CategoryProduct> findByProducts(List<Product> products);


}
