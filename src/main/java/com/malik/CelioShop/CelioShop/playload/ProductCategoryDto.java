package com.malik.CelioShop.CelioShop.playload;

import com.malik.CelioShop.CelioShop.entity.Product;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
public class ProductCategoryDto {

    private Long id;
    private String name;
    private String description;
    private Set<Product> productSet;
}
