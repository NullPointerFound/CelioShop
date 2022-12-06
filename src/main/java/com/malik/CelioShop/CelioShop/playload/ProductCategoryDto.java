package com.malik.CelioShop.CelioShop.playload;

import lombok.Data;

import java.util.Set;

@Data
public class ProductCategoryDto {

    private Long id;
    private String name;
    private String description;
    private Set<ProductDto> productSet;
}
