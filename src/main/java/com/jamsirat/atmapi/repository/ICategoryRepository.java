package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.CategoryProduct;
import com.jamsirat.atmapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryProduct,Long> {

    Optional <CategoryProduct> findByProducts(Product productId);
}
