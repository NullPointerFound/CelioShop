package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.ReviewDto;
import com.malik.CelioShop.CelioShop.service.ProductService;
import com.malik.CelioShop.CelioShop.service.ReviewService;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto>> getAllReviews(){
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long reviewId){

        return new ResponseEntity<>(reviewService.getReviewById(reviewId),HttpStatus.OK);
    }

    @GetMapping("/product/{productId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviewsByProductId(@PathVariable Long productId){
        return new ResponseEntity<>(reviewService.getReviewsByProductId(productId), HttpStatus.OK);
    }

    @PostMapping("product/{productId}/review")
    public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto reviewDto,
                                                  @PathVariable Long productId){

        return new ResponseEntity(reviewService.createReview(reviewDto,productId), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/review/delete/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId){

        reviewService.deleteReviewById(reviewId);
        return ResponseEntity.ok("Review has been deleted");
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteMyReviewById(@PathVariable Long reviewId){

        reviewService.deleteMyReviewById(reviewId);
        return ResponseEntity.ok("Review has been deleted");
    }


    @PutMapping("review/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@RequestBody ReviewDto reviewDto,
                                                  @PathVariable Long reviewId){


        return new ResponseEntity<>(reviewService.updateReview(reviewDto,reviewId), HttpStatus.OK);
    }

}
