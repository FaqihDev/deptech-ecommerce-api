package com.jamsirat.atmapi.model.inventory;

import com.jamsirat.atmapi.model.Base.AAuditableBase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
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
    private List<Product> products;


}