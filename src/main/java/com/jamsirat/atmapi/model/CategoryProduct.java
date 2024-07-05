package com.jamsirat.atmapi.model;

import com.jamsirat.atmapi.model.Base.AAuditableBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category_product")
public class CategoryProduct extends AAuditableBase {


    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_product")
    private String categoryProduct;


}