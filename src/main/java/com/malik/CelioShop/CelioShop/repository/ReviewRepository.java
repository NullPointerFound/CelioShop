package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findByProduct(Product product);

    @Query("UPDATE Review r SET r.vote = " +
            "COALESCE((SELECT SUM(v.votes) from ReviewVote v WHERE v.review.id=?1),0) WHERE r.id = ?1")
    @Modifying
    void updateReviewVoteCount(Long reviewId);

    @Query("SELECT r.vote FROM Review r WHERE r.id=?1")
    Integer getVoteCount(Long reviewId);
}
