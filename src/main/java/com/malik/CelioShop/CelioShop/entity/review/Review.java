package com.malik.CelioShop.CelioShop.entity.review;

import com.malik.CelioShop.CelioShop.entity.Product;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "headline")
    private String headline;

    @Column(name = "comment")
    private String comment;

    @Column(name = "vote")
    private Integer vote;

    @CreationTimestamp
    @Column(name = "create_date_time")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private LocalDateTime updatedDate;

    @Column(name = "rate")
    private Integer rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_id")
    private Product product;
}
