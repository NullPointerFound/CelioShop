package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.Utils.Mapper;
import com.malik.CelioShop.CelioShop.entity.Product;
import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductCategoryDto;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.repository.ProductCategoryRepository;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product newProduct = Mapper.mapToProduct(productDto);

        // for testing purposes only
        // newProduct.setId(productDto.getId());

        // Get the ID of the category
        Long categoryId = productDto.getProductCategoryId();

        // check if the category id is null
        if (categoryId != null) {

            ProductCategory category = productCategoryRepository
                    .findById(productDto.getProductCategoryId())
                    .orElseThrow(
                            () -> new RuntimeException("No category found")
                    );

            newProduct.setProductCategory(category);
        }


        Product savedProduct = productRepository.save(newProduct);

        return Mapper.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new RuntimeException("Product doesn't exist")
        );

        return Mapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtoList = products.stream().map( product -> Mapper.mapToProductDto(product)).collect(Collectors.toList());

        return productDtoList;
    }

    @Override
    public void deleteProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new RuntimeException("Product doesn't exist")
        );

        productRepository.delete(product);
    }

    @Override
    public void updateProductById(Long productId) {

    }


}
