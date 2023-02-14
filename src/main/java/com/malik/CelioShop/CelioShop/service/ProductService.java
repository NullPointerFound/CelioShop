package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ProductService {
    ProductDto createProduct(ProductDto productDto, String categoryName);

    ProductDto getProductById(Long productId);

    List<ProductDto> getAllProducts();

    void deleteProductById(Long productId);

    void updateProductById(Long productId);

    List<ProductDto> getProductsByCategoryId(Long categoryId);

    List<ProductDto> searchProduct(String query);
}
