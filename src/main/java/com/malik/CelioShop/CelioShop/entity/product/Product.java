package com.malik.CelioShop.CelioShop.entity.product;

import com.malik.CelioShop.CelioShop.entity.review.Review;
import com.malik.CelioShop.CelioShop.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "image_url")
    private String imgUrl;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Column(name = "rate_count")
    private Integer rateCount;

    @Column(name = "average_rate")
    private Float avgRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,
                cascade = {CascadeType.PERSIST,
                        CascadeType.MERGE,
                        CascadeType.REFRESH}
            ,optional = true)
    @JoinColumn(name = "Category_id")
    private ProductCategory productCategory;


    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Review> reviewSet;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
    private Set<ProductMedia> productMedia;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) && getName().equals(product.getName()) && getDescription().equals(product.getDescription()) && getSku().equals(product.getSku()) && getPrice().equals(product.getPrice()) && getQuantity().equals(product.getQuantity()) && Objects.equals(getImgUrl(), product.getImgUrl()) && Objects.equals(getCreationDate(), product.getCreationDate()) && Objects.equals(getUpdateDate(), product.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getSku(), getPrice(), getQuantity(), getImgUrl(), getCreationDate(), getUpdateDate());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", imgUrl='" + imgUrl + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", productCategory=" + productCategory +
                '}';
    }
}
