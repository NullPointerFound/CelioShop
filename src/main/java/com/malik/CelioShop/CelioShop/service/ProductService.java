package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.playload.ProductDtoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ProductService {
    ProductDtoResponse createProduct(ProductDto productDto, String categoryName);

    ProductDtoResponse getProductById(Long productId);

    List<ProductDtoResponse> getAllProducts();

    void deleteProductById(Long productId);

    ProductDtoResponse updateProductById(Long productId, ProductDto productDto);

    List<ProductDtoResponse> getProductsByCategoryId(Long categoryId);

    List<ProductDtoResponse> searchProduct(String query);
}
