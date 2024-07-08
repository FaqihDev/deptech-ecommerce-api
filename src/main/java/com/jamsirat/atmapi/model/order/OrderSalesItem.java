package com.jamsirat.atmapi.model.order;


import com.jamsirat.atmapi.model.Base.AAuditableBase;
import com.jamsirat.atmapi.model.inventory.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@Table(name = "order_sales_item")
public class OrderSalesItem extends AAuditableBase {

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "order_sales_item_product",
            joinColumns = @JoinColumn(name = "order_sales_item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id")
    )
    private List<Product> products;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_sales_id")
    private OrderSales orderSales;


}
