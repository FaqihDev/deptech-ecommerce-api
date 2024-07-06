package com.jamsirat.atmapi.model;

import com.jamsirat.atmapi.model.Base.AAuditableBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Where(clause = "is_deleted = false")
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product extends AAuditableBase {


    @Column(name = "product_name")
    private String productName;

    @Column(name = "description_product")
    private String descriptionProduct;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private CategoryProduct productCategory;

    @Column(name = "stock_product")
    private Integer stockProduct;


}