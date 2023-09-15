package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.review.Review;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.CelioShopApiException;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.playload.product.ProductDtoResponse;
import com.malik.CelioShop.CelioShop.playload.review.PageReviewDtoResponse;
import com.malik.CelioShop.CelioShop.playload.review.ReviewDto;
import com.malik.CelioShop.CelioShop.playload.ReviewDtoResponse;
import com.malik.CelioShop.CelioShop.playload.product.PageProductDtoResponse;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.repository.ReviewRepository;
import com.malik.CelioShop.CelioShop.service.ReviewService;
import com.malik.CelioShop.CelioShop.service.ServiceHelper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ReviewDtoResponse getReviewById(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new ResourceNotFound("Review","ID",reviewId)
        );

        return modelMapper.map(review,ReviewDtoResponse.class);
    }

    @Override
    @Transactional
    public ReviewDtoResponse createReview(ReviewDto reviewDto,Long productId) {

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

        return modelMapper.map(savedReview,ReviewDtoResponse.class);
    }

    @Override
    public void deleteReviewById(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new ResourceNotFound("Review","ID",reviewId)
        );

        reviewRepository.delete(review);
    }

    @Override
    public PageReviewDtoResponse getAllReviews(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        // Retrieve the products
        Page<Review> reviewPage = reviewRepository.findAll(pageable);


        PageReviewDtoResponse pageReviewDtoResponse = getPageReviewDtoResponse(reviewPage);

        return pageReviewDtoResponse;

    }
    @Override
    public PageReviewDtoResponse getReviewsByProductId(Long productId, int pageNumber, int pageSize, String sortBy, String sortDir) {

        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFound("Product","ID",productId)
        );

        List<Review> reviews = reviewRepository.findByProduct(product);

        Page<Review> pageableReviews = convertReviewListToPageable(pageNumber, pageSize, sortBy, sortDir, reviews);

        PageReviewDtoResponse pageReviewDtoResponse = getPageReviewDtoResponse(pageableReviews);

        return pageReviewDtoResponse;

    }

    private  Page<Review> convertReviewListToPageable(int pageNumber, int pageSize, String sortBy, String sortDir, List<Review> reviewList) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageRequest = createPageRequestUsing(pageNumber, pageSize);

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), reviewList.size());

        List<Review> pageContent = reviewList.subList(start, end);

        Page<Review> pageableReviews =  new PageImpl<>(pageContent, pageRequest, reviewList.size());

        return pageableReviews;


    }
    private Pageable createPageRequestUsing(int page, int size) {
        return PageRequest.of(page, size);
    }

    @Override
    public void deleteMyReviewById(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new ResourceNotFound("Review","ID",reviewId)
        );

        // find the authenticated user
        User user = serviceHelper.getAuthenticatedUser();

        if (!review.getUser().equals(user)){
            throw new CelioShopApiException("you can't delete a comment that it's not yours", HttpStatus.UNAUTHORIZED);
        }

        reviewRepository.delete(review);
    }

    @Override
    public ReviewDtoResponse updateMyReview(ReviewDto reviewDto, Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new ResourceNotFound("Review","ID",reviewId)
        );

        // find the authenticated user
        User user = serviceHelper.getAuthenticatedUser();

        if (!review.getUser().equals(user)){
            throw new CelioShopApiException("you can't update a comment that it's not yours", HttpStatus.UNAUTHORIZED);
        }

        if (review.getHeadline() != null){
            review.setHeadline(review.getHeadline());
        }

        if (review.getComment() != null){
            review.setComment(review.getComment());
        }

        if (review.getRate() != null){
            review.setRate(review.getRate());
        }

        return modelMapper.map(reviewRepository.save(review),ReviewDtoResponse.class);
    }

    // extract from Page<Product> products and convert them to ProductDto and assign the result to PageProductDtoResponse
    private PageReviewDtoResponse getPageReviewDtoResponse(Page<Review> reviewPage) {

        List<Review> reviewList = reviewPage.getContent();

        // Convert the products found to DTOs
        List<ReviewDto> reviewDtoList = reviewList.stream().map(
                review -> modelMapper.map(review, ReviewDto.class)
        ).collect(Collectors.toList());


        // Create PageProductDtoResponse instance
        PageReviewDtoResponse pageReviewDtoResponse = new PageReviewDtoResponse();
        pageReviewDtoResponse.setPageNumber(reviewPage.getNumber());
        pageReviewDtoResponse.setPageSize(reviewPage.getSize());
        pageReviewDtoResponse.setTotalElements(reviewPage.getTotalElements());
        pageReviewDtoResponse.setTotalPages(reviewPage.getTotalPages());
        pageReviewDtoResponse.setLast(reviewPage.isLast());
        pageReviewDtoResponse.setContent(reviewDtoList);
        return pageReviewDtoResponse;
    }
}
