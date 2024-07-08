package com.jamsirat.atmapi.model.inventory;

import com.jamsirat.atmapi.model.Base.AAuditableBase;
import com.jamsirat.atmapi.model.order.OrderSalesItem;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Where(clause = "is_deleted = false")
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Product extends AAuditableBase {


    @Column(name = "product_name")
    private String productName;

    @Column(name = "description_product")
    private String descriptionProduct;

    @Column(name = "image")
    private String image;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "product_category_id")
    private CategoryProduct productCategory;

    @Column(name = "stock_product")
    private Integer stockProduct;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(mappedBy = "products")
    private List<OrderSalesItem> orderSalesItems;

}