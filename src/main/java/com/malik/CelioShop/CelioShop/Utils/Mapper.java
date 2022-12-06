package com.malik.CelioShop.CelioShop.Utils;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.product.ProductCategory;
import com.malik.CelioShop.CelioShop.entity.review.Review;
import com.malik.CelioShop.CelioShop.playload.ProductCategoryDto;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.playload.ReviewDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class Mapper {


    @Autowired
    private static ModelMapper modelMapper;

    // method to map from Product Entity to Product DTO
    public static ProductDto mapToProductDto(Product product){

        return modelMapper.map(product,ProductDto.class);

//        if(product != null){
//            ProductDto productDto = new ProductDto();
//
//            productDto.setId(product.getId());
//            productDto.setName(product.getName());
//            productDto.setDescription(product.getDescription());
//            productDto.setSku(product.getSku());
//            productDto.setPrice(product.getPrice());
//            productDto.setQuantity(product.getQuantity());
//            productDto.setImgUrl(product.getImgUrl());
//            if(product.getProductCategory() != null){
//                productDto.setProductCategoryId(product.getProductCategory().getId());
//            }
//
//            productDto.setCreationDate(product.getCreationDate());
//            productDto.setUpdateDate(product.getUpdateDate());
//            return productDto;
//        }
//        return null;

    }

    // method to map from ProductCategory Entity to ProductCategory Dto
    public static ProductCategoryDto mapToProductCategoryDto(ProductCategory productCategory){

        return modelMapper.map(productCategory,ProductCategoryDto.class);

//        if ( productCategory != null){
//            ProductCategoryDto productCategoryDto = new ProductCategoryDto();
//
//            productCategoryDto.setId(productCategory.getId());
//            productCategoryDto.setName(productCategory.getName());
//            productCategoryDto.setDescription(productCategory.getDescription());
//
//            if ( productCategory.getProductSet() != null )
//                productCategoryDto.setProductSet(
//                        productCategory
//                                .getProductSet()
//                                .stream()
//                                .map( product -> mapToProductDto(product)).collect(Collectors.toSet()));
//
//            return productCategoryDto;
//        }
//        return null;
    }


    // method to map from ProductCategory Entity to ProductCategory Dto
    public static ReviewDto mapToReviewDto(Review review){

        return modelMapper.map(review,ReviewDto.class);
//        if ( review != null){
//            ReviewDto reviewDto = new ReviewDto();
//
//            reviewDto.setId(review.getId());
//            reviewDto.setHeadline(review.getHeadline());
//            reviewDto.setComment(review.getComment());
//            reviewDto.setCreationDate(review.getCreationDate());
//            reviewDto.setUpdatedDate(review.getUpdatedDate());
//            return reviewDto;
//        }
//        return null;

    }



    // method to map from Product DTO to Product Entity
    public static Product mapToProduct(ProductDto productDto){

        return modelMapper.map(productDto,Product.class);
//        if(productDto != null){
//            Product product = new Product();
//            // for testing purposes only
//            //product.setId(productDto.getId());
//
//            product.setName(productDto.getName());
//            product.setDescription(productDto.getDescription());
//            product.setSku(productDto.getSku());
//            product.setPrice(productDto.getPrice());
//            product.setQuantity(productDto.getQuantity());
//            product.setImgUrl(productDto.getImgUrl());
//            return product;
//        }
//        return null;
    }


    // method to map from ProductCategory DTO to ProductCategory Entity
    public static ProductCategory mapToProductCategory(ProductCategoryDto productCategoryDto){

        return modelMapper.map(productCategoryDto,ProductCategory.class);
//        if ( productCategoryDto != null ){
//            ProductCategory productCategory = new ProductCategory();
//            // for testing purposes only
//            //productCategory.setId(productCategoryDto.getId());
//
//            productCategory.setName(productCategoryDto.getName());
//            productCategory.setDescription(productCategoryDto.getDescription());
//            return productCategory;
//        }
//        return null;
    }

    // method to map from Review DTO to Review Entity
    public static Review mapToReview(ReviewDto reviewDto){

        return modelMapper.map(reviewDto,Review.class);
//        if(reviewDto != null){
//            Review review = new Review();
//            // for testing purposes only
//            //review.setId(reviewDto.getId());
//
//            review.setHeadline(reviewDto.getHeadline());
//            review.setComment(reviewDto.getComment());
//            return review;
//        }
//        return null;
    }


}
