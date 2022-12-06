package com.malik.CelioShop.CelioShop.entity.order;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subtotal;

    private BigDecimal shippingCost;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
