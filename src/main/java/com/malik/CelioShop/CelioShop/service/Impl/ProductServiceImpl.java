package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.Product;
import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.service.ProductService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public ProductDto addProduct(ProductDto productDto, ProductCategory productCategory) {

        Product newProduct = mapToProduct(productDto,productCategory);
        Product savedProduct = productRepository.save(newProduct);

        return mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new RuntimeException("Product doesn't exist")
        );

        return mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtoList = products.stream().map( product -> mapToProductDto(product)).collect(Collectors.toList());

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

    // method to map from Product Entity to Product DTO
    private ProductDto mapToProductDto(Product product){

        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setSku(product.getSku());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setImgUrl(product.getImgUrl());
        productDto.setProductCategoryId(product.getProductCategory().getId());
        productDto.setCreationDate(product.getCreationDate());
        productDto.setUpdateDate(product.getUpdateDate());

        return productDto;
    }

    // method to map from Product DTO to Product Entity
    private Product mapToProduct(ProductDto productDto,ProductCategory productCategory){
        Product product = new Product();

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setImgUrl(productDto.getImgUrl());
        product.setProductCategory(productCategory);

        return product;
    }
}
