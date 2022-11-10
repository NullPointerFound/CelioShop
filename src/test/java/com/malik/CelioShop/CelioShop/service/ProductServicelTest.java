package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.Utils.Mapper;
import com.malik.CelioShop.CelioShop.entity.Product;
import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.repository.ProductCategoryRepository;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.service.Impl.ProductServiceImpl;
import com.malik.CelioShop.CelioShop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServicelTest {

    private ProductService productService;
    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;

    @BeforeEach
    public void setup(){
        productRepository = Mockito.mock(ProductRepository.class);
        productCategoryRepository = Mockito.mock(ProductCategoryRepository.class);
        productService = new ProductServiceImpl(productRepository,productCategoryRepository);
    }

    // JUnit test for createProduct method
    @Test
    void givenProductObject_whenCreateProduct_thenReturnProductObject() {

        // given - precondition or setup
        ProductCategory category = ProductCategory.builder()
                .id(3L)
                .name("Toys")
                .description("Category for toys")
                .build();

        Product newProduct = Product.builder()
                .id(55L)
                .name("Car with remote for kids")
                .description("Car with remote for kids")
                .sku("4658")
                .price(BigDecimal.valueOf(79.56))
                .quantity(45)
                .imgUrl("google.com")
                .build();


        BDDMockito.given(productCategoryRepository.findById(1L))
                .willReturn(Optional.ofNullable(category));

        BDDMockito.given(productRepository.save(newProduct)).willReturn(newProduct);

        // when - action or the behaviour that we are going test
        ProductDto newProductDto = Mapper.mapToProductDto(newProduct);
        newProductDto.setId(newProduct.getId());
        ProductDto savedProductDto = productService.createProduct(newProductDto);

        // then - verify the output
        assertThat(savedProductDto).isNotNull();
    }

    @Test
    void getProductById() {
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