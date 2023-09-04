package com.malik.CelioShop.CelioShop.entity.review;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


/*
    This class for Review entity, it has a relationship of many-to-one with Product entity,
    and relationship of many-to-one with User entity,
    only authenticated user can add reviews, edit their reviews and delete their reviews,
    while all the no unauthenticated user can see them,
    and only users with admin privileges can edit, delete and update any review.
 */
@Data
@Entity(name = "Review")
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

    @Column(name = "votes")
    private Long vote;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean visible;

}
