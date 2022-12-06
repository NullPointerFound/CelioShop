package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.review.Review;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.playload.ReviewDto;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.repository.ReviewRepository;
import com.malik.CelioShop.CelioShop.repository.UserRepository;
import com.malik.CelioShop.CelioShop.service.ReviewService;
import com.malik.CelioShop.CelioShop.service.ServiceHelper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private ServiceHelper serviceHelper;

    @Override
    public List<ReviewDto> getAllReviews() {

        List<Review> allReviews = reviewRepository.findAll();

        List<ReviewDto> allReviewsDto = allReviews.stream().map(
                review -> modelMapper.map(review,ReviewDto.class)
        ).collect(Collectors.toList());

        return allReviewsDto;
    }

    @Override
    public ReviewDto getReviewById(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new ResourceNotFound("Review","ID",reviewId)
        );

        return modelMapper.map(review,ReviewDto.class);
    }

    @Override
    @Transactional
    public ReviewDto createReview(ReviewDto reviewDto,Long productId) {

        Product foundProduct = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFound("Product","ID",productId)
        );
        Review newReview = modelMapper.map(reviewDto,Review.class);
        newReview.setProduct(foundProduct);

        // find the authenticated user
        User user = serviceHelper.getAuthenticatedUser();

        newReview.setUser(user);

        Review savedReview = reviewRepository.save(newReview);

        productRepository.updateAvgRateAndRateCount(productId);

        return modelMapper.map(savedReview,ReviewDto.class);
    }

    @Override
    public void deleteReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new ResourceNotFound("Review","ID",reviewId)
        );

        reviewRepository.delete(review);
    }

    @Override
    public List<ReviewDto> getReviewsByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFound("Product","ID",productId)
        );

        List<Review> reviews = reviewRepository.findByProduct(product);
        List<ReviewDto> reviewDtoList = reviews.stream().map(
                review -> modelMapper.map(review,ReviewDto.class)
        ).collect(Collectors.toList());

        return reviewDtoList;
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto, Long reviewId) {
        return null;
    }
}
