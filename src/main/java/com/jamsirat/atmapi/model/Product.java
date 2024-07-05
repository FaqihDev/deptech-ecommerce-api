package com.jamsirat.atmapi.model;

import com.jamsirat.atmapi.model.Base.AAuditableBase;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product extends AAuditableBase {


    @Column(name = "product_name")
    private String productName;

    @Column(name = "description_product")
    private String descriptionProduct;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_category")
    private CategoryProduct productCategory;

    @Column(name = "stock_product")
    private Integer stockProduct;


}