package com.malik.CelioShop.CelioShop.playload.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class ProductCategoryDto {

    private Long id;
    @NotEmpty
    @Size(min = 4,max = 20)
    private String name;
    private String description;
}
