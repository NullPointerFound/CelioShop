package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.product.PageProductDtoResponse;
import com.malik.CelioShop.CelioShop.playload.review.PageReviewDtoResponse;
import com.malik.CelioShop.CelioShop.playload.review.ReviewDto;
import com.malik.CelioShop.CelioShop.playload.ReviewDtoResponse;

import java.util.List;

public interface ReviewService {
    PageReviewDtoResponse getAllReviews(int pageNumber, int pageSize, String sortBy, String sortDir);

    ReviewDtoResponse getReviewById(Long reviewId);

    ReviewDtoResponse createReview(ReviewDto reviewDto, Long productId);

    void deleteReviewById(Long reviewId);

    ReviewDtoResponse updateMyReview(ReviewDto reviewDto, Long reviewId);

    PageReviewDtoResponse getReviewsByProductId(Long productId, int pageNumber, int pageSize, String sortBy, String sortDir);

    void deleteMyReviewById(Long reviewId);
}
