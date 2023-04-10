package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReviews();

    ReviewDto getReviewById(Long reviewId);

    ReviewDto createReview(ReviewDto reviewDto, Long productId);

    void deleteReviewById(Long reviewId);

    ReviewDto updateMyReview(ReviewDto reviewDto, Long reviewId);

    List<ReviewDto> getReviewsByProductId(Long productId);

    void deleteMyReviewById(Long reviewId);
}
