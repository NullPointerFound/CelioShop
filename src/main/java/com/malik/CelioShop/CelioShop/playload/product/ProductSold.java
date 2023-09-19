package com.malik.CelioShop.CelioShop.playload.product;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductSold {

    private Long productId;
    private String productName;
    private BigDecimal productCost;
    private String productSku;
    private Integer quantityInStock;
    private Integer productRateCount;
    private Integer quantitySold;
    private LocalDateTime saleDate;
    private BigDecimal subtotal;
    private Long orderId;

}
