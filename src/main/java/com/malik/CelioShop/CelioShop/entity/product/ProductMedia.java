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

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    public ProductMedia(String name, String type, String filePath) {
        this.name = name;
        this.type = type;
        this.filePath = filePath;
    }
}
