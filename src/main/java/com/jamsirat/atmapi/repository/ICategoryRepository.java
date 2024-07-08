package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.inventory.CategoryProduct;
import com.jamsirat.atmapi.model.inventory.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryProduct,Long> {

    Optional<CategoryProduct> findByProducts(Product product);

    List<CategoryProduct> findAllByProducts(List<Product> products);


}
