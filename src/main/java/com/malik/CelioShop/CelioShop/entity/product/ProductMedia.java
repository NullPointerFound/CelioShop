package com.malik.CelioShop.CelioShop.entity.product;


import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class ProductMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String type;

    private String path;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
