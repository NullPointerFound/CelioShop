package com.malik.CelioShop.CelioShop.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Comment")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "headline")
    private String headline;

    @Column(name = "comment")
    private String comment;

    @CreationTimestamp
    @Column(name = "Create_date_time")
    private LocalDateTime creationDate;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Product_id")
    private Product product;
}
