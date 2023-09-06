package com.malik.CelioShop.CelioShop.playload.product;

import com.malik.CelioShop.CelioShop.entity.product.ProductCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class ProductDtoResponse {

    private Long id;

    private String name;

    private String description;

    private String sku;

    private BigDecimal price;

    private Integer quantity;

    private String imgUrl;

    private ProductCategory category;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;

    private Integer rateCount;

    private Float avgRate;
}
