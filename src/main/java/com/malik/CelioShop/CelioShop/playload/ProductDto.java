package com.malik.CelioShop.CelioShop.playload;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    private Integer quantity;
    private String imgUrl;
    private Long categoryId;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}
