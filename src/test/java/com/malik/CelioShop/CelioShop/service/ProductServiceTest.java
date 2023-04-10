package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.product.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.playload.ProductDtoResponse;
import com.malik.CelioShop.CelioShop.repository.ProductCategoryRepository;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductCategoryRepository productCategoryRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    private Product newProduct;

    private ProductCategory category;

    @BeforeEach
    public void setup(){
        /*
         productRepository = Mockito.mock(ProductRepository.class);
         productCategoryRepository = Mockito.mock(ProductCategoryRepository.class);
        productService = new ProductServiceImpl(productRepository,productCategoryRepository);
        */


        category = ProductCategory.builder()
                .id(3L)
                .name("Toys")
                .description("Category for toys")
                .build();

        newProduct = Product.builder()
                .id(55L)
                .name("Car with remote for kids")
                .description("Car with remote for kids")
                .sku("4658")
                .price(BigDecimal.valueOf(79.56))
                .quantity(45)
                .productCategory(category)
                .imgUrl("google.com")
                .build();
    }

    // JUnit test for createProduct method
    @Test
    void givenProductObject_whenCreateProduct_thenReturnProductObject() {

        /*
        Before running the Test the two lines {for testing purposes only}
        in Mapper class need to be uncommented
         */

        // given - precondition or setup

        BDDMockito.given(productCategoryRepository.findById(category.getId()))
                .willReturn(Optional.of(category));

        BDDMockito.given(productRepository.save(newProduct)).willReturn(newProduct);

        // when - action or the behaviour that we are going test
//        ProductDto newProductDto = Mapper.mapToProductDto(newProduct);

        //ProductDto savedProductDto = productService.createProduct(newProductDto);

        // then - verify the output
        //assertThat(savedProductDto).isNotNull();
    }

    @Test
    void givenProductId_whenGetProductById_thenProductObject() {

        // Given - precondition or setup

        BDDMockito
                .given(productRepository.findById(newProduct.getId()))
                .willReturn(Optional.of(newProduct));


        // When - action or the behaviour that we are going test
        ProductDtoResponse returnedProductDto = productService.getProductById(newProduct.getId());


        // Then -  verify the output
        assertThat(returnedProductDto).isNotNull();

    }

    @Test
    void getAllProducts() {
    }

    @Test
    void deleteProductById() {
    }

    @Test
    void updateProductById() {
    }
}