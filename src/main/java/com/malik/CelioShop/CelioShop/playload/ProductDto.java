package com.malik.CelioShop.CelioShop.playload;

import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Long productCategoryId;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}
