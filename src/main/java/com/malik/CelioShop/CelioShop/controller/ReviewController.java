package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.product.PageProductDtoResponse;
import com.malik.CelioShop.CelioShop.playload.review.PageReviewDtoResponse;
import com.malik.CelioShop.CelioShop.playload.review.ReviewDto;
import com.malik.CelioShop.CelioShop.playload.ReviewDtoResponse;
import com.malik.CelioShop.CelioShop.service.ReviewService;
import com.malik.CelioShop.CelioShop.utils.AppConstants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private ReviewService reviewService;


    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/reviews")
    public ResponseEntity<PageReviewDtoResponse> getAllReviews(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return new ResponseEntity<>(reviewService.getAllReviews(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<ReviewDtoResponse> getReviewById(@PathVariable Long reviewId){

        return new ResponseEntity<>(reviewService.getReviewById(reviewId),HttpStatus.OK);
    }

    @GetMapping("/product/{productId}/reviews")
    public ResponseEntity<PageReviewDtoResponse> getReviewsByProductId(@PathVariable Long productId,
                                                                         @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                                                         @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                                         @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                                         @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return new ResponseEntity<>(reviewService.getReviewsByProductId(productId,pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PostMapping("product/{productId}/review")
    public ResponseEntity<ReviewDtoResponse> createReview(@Valid @RequestBody ReviewDto reviewDto,
                                                  @PathVariable Long productId){

        return new ResponseEntity(reviewService.createReview(reviewDto,productId), HttpStatus.CREATED);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/review/delete/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId){

        reviewService.deleteReviewById(reviewId);
        return ResponseEntity.ok("Review has been deleted");
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteMyReviewById(@PathVariable Long reviewId){

        reviewService.deleteMyReviewById(reviewId);
        return ResponseEntity.ok("Review has been deleted");
    }


    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("review/{reviewId}")
    public ResponseEntity<ReviewDtoResponse> updateMyReview(@RequestBody ReviewDto reviewDto,
                                                  @PathVariable Long reviewId){


        return new ResponseEntity<>(reviewService.updateMyReview(reviewDto,reviewId), HttpStatus.OK);
    }

}
