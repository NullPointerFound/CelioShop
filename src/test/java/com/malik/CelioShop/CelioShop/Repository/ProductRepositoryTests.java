package com.malik.CelioShop.CelioShop.Repository;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.product.ProductCategory;
import com.malik.CelioShop.CelioShop.entity.review.Review;
import com.malik.CelioShop.CelioShop.repository.ProductCategoryRepository;
import com.malik.CelioShop.CelioShop.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

//    @Autowired
//    private com.malik.CelioShop.CelioShop.repository.ProductRepository productRepository;
//
//    @Autowired
//    private ProductCategoryRepository productCategoryRepository;
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @DisplayName("JUnit test for save product operation")
//    @Test
//    public void givenProductObject_whenSaved_thenReturnSavedProduct(){
//
//        // given - precondition or setup
//        Product newProduct = Product.builder()
//                .name("Car with remote for kids")
//                .description("Car with remote for kids")
//                .sku("4658")
//                .price(BigDecimal.valueOf(79.56))
//                .quantity(45)
//                .imgUrl("google.com")
//                .build();
//
//
//        // when - action or the behavior that we are going to test
//        Product savedProduct = productRepository.save(newProduct);
//
//        // then - verify the output
//        Assertions.assertThat(savedProduct).isNotNull();
//        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
//        }
//
//        // JUit test for get all products
//        @Test
//        public void givenProductList_whenFindAll_thenProductList(){
//
//            // given - precondition or setup
//            Product newProduct = Product.builder()
//                    .name("Car with remote for kids")
//                    .description("Car with remote for kids")
//                    .sku("4658")
//                    .price(BigDecimal.valueOf(79.56))
//                    .quantity(45)
//                    .imgUrl("google.com")
//                    .build();
//
//            Product anotherProduct = Product.builder()
//                    .name("boat with remote for kids")
//                    .description("boat with remote for kids")
//                    .sku("4660")
//                    .price(BigDecimal.valueOf(99.56))
//                    .quantity(20)
//                    .imgUrl("google.com")
//                    .build();
//
//            productRepository.save(newProduct);
//            productRepository.save(anotherProduct);
//
//            // when - action or the behaviour that we are going to test
//
//            List<Product> productList = productRepository.findAll();
//
//            // then - verify the output
//            Assertions.assertThat(productList).isNotNull();
//            Assertions.assertThat(productList.size()).isEqualTo(2);
//        }
//
//        // junit test for get employee by id operation
//        @Test
//        public void givenProductObject_whenFindById_thenReturnProductObject(){
//
//            //given - precondition or setup
//            Product newProduct = Product.builder()
//                    .name("Car with remote for kids")
//                    .description("Car with remote for kids")
//                    .sku("4658")
//                    .price(BigDecimal.valueOf(79.56))
//                    .quantity(45)
//                    .imgUrl("google.com")
//                    .build();
//
//            Product savedProduct = productRepository.save(newProduct);
//
//            // when - action or the behaviour that we are going to test
//            Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());
//
//            // then - verify the output
//            Assertions.assertThat(foundProduct).isNotNull();
//            Assertions.assertThat(savedProduct.getId()).isEqualTo(foundProduct.get().getId());
//
//        }
//
//
//        // JUnit test for get Products by Category
//        @Test
//        public void givenProductCategory_whenFindByProductCategory_thenReturnProductList(){
//
//            // given - precondition or setup
//            ProductCategory category = ProductCategory.builder()
//                    .name("toys")
//                    .description("category for kids toy")
//                    .build();
//
//            Product newProduct = Product.builder()
//                    .name("Car with remote for kids")
//                    .description("Car with remote for kids")
//                    .sku("4658")
//                    .price(BigDecimal.valueOf(79.56))
//                    .quantity(45)
//                    .imgUrl("google.com")
//                    .productCategory(category)
//                    .build();
//
//            //ProductCategory savedCategory = productCategoryRepository.save(category);
//
//            Product savedProduct = productRepository.save(newProduct);
//
//            // when - action or the behaviour that we are going to test
//
//            List<Product> productList = productRepository.findByProductCategory(category);
//
//            // then - verify the output
//
//            Assertions.assertThat(productList).isNotNull();
//            Assertions.assertThat(productList.size()).isEqualTo(1);
//
//        }
//
//        @Test
//        public void givenProductId_whenUpdateAvgRateAndRateCount_thenUpdateAvgRateAndRateCount(){
//
//            //given - precondition or setup
//            Product newProduct = Product.builder()
//                    .name("Car with remote for kids")
//                    .description("Car with remote for kids")
//                    .sku("4658")
//                    .price(BigDecimal.valueOf(79.56))
//                    .quantity(45)
//                    .imgUrl("google.com")
//                    .build();
//
//            Review review = new Review();
//            review.setHeadline("I liked the product");
//            review.setComment("the product is Amazing");
//            review.setRate(5);
//            review.setProduct(newProduct);
//
//            productRepository.save(newProduct);
//            reviewRepository.save(review);
//
//            // when - action or the behaviour that we are going to test
//
//            productRepository.updateAvgRateAndRateCount(newProduct.getId());
//
//
//            Optional<Product> updatedProduct = productRepository.findById(newProduct.getId());
//
//            Optional<Review> savedReview = reviewRepository.findById(review.getId());
//
//            // then - verify the output
//            Assertions.assertThat(savedReview.get()).isNotNull();
//            Assertions.assertThat(updatedProduct.get().getAvgRate()).isNull();
//            Assertions.assertThat(updatedProduct.get().getRateCount()).isNull();
//
//        }

}
