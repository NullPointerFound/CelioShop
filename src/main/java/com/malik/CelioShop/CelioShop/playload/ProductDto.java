package com.malik.CelioShop.CelioShop.playload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDto {


    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String sku;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer quantity;
    private String imgUrl;
    private Long categoryId;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}
