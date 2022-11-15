package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.Product;
import com.malik.CelioShop.CelioShop.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findByProduct(Product product);
}
