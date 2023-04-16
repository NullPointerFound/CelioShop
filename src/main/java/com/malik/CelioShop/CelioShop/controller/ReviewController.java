package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.ReviewDto;
import com.malik.CelioShop.CelioShop.playload.ReviewDtoResponse;
import com.malik.CelioShop.CelioShop.service.ProductService;
import com.malik.CelioShop.CelioShop.service.ReviewService;
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
    public ResponseEntity<List<ReviewDtoResponse>> getAllReviews(){
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<ReviewDtoResponse> getReviewById(@PathVariable Long reviewId){

        return new ResponseEntity<>(reviewService.getReviewById(reviewId),HttpStatus.OK);
    }

    @GetMapping("/product/{productId}/reviews")
    public ResponseEntity<List<ReviewDtoResponse>> getReviewsByProductId(@PathVariable Long productId){
        return new ResponseEntity<>(reviewService.getReviewsByProductId(productId), HttpStatus.OK);
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
