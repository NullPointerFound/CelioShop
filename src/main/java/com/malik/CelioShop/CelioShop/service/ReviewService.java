package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.ReviewDto;
import com.malik.CelioShop.CelioShop.playload.ReviewDtoResponse;

import java.util.List;

public interface ReviewService {
    List<ReviewDtoResponse> getAllReviews();

    ReviewDtoResponse getReviewById(Long reviewId);

    ReviewDtoResponse createReview(ReviewDto reviewDto, Long productId);

    void deleteReviewById(Long reviewId);

    ReviewDtoResponse updateMyReview(ReviewDto reviewDto, Long reviewId);

    List<ReviewDtoResponse> getReviewsByProductId(Long productId);

    void deleteMyReviewById(Long reviewId);
}
