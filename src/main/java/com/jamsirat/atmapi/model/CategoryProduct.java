package com.jamsirat.atmapi.model;

import com.jamsirat.atmapi.model.Base.AAuditableBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@Table(name = "category_product")
public class CategoryProduct extends AAuditableBase {


    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description_category")
    private String descriptionCategory;

    @OneToMany(mappedBy = "productCategory")
    private Product product;


}