package com.malik.CelioShop.CelioShop.playload.statistics;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class UserStats {

    private Long userId;
    private String username;
    private LocalDateTime userJoinedDate;
    private BigDecimal orderSubTotal;
    private BigDecimal taxPaid;
    private BigDecimal shippingCost;
    private LocalDateTime saleDate;
    private Integer quantityBought;
    private Product product;


}
