package com.malik.CelioShop.CelioShop.playload.product;

import com.malik.CelioShop.CelioShop.entity.product.ProductCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDto {

    private Long id;
    @NotEmpty
    @Size(min = 4, max = 100)
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String sku;

    @Min(value=0, message= "price couldn't be negative or empty")
    private BigDecimal price;

    @Min(value=0, message= "quantity couldn't be negative or empty")
    private Integer quantity;

    private String imgUrl;

    private Integer categoryId;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;
}
