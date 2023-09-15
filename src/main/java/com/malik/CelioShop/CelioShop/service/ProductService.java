package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.product.PageProductDtoResponse;
import com.malik.CelioShop.CelioShop.playload.product.ProductDto;
import com.malik.CelioShop.CelioShop.playload.product.ProductDtoResponse;

import java.util.List;


public interface ProductService {
    ProductDtoResponse createProduct(ProductDto productDto, String categoryName);

    ProductDtoResponse getProductById(Long productId);

    PageProductDtoResponse getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir);

    void deleteProductById(Long productId);

    ProductDtoResponse updateProductById(Long productId, ProductDto productDto);

    PageProductDtoResponse getProductsByCategoryId(Long categoryId, int pageNumber, int pageSize, String sortBy, String sortDir);

    PageProductDtoResponse searchProduct(String query, int pageNumber, int pageSize, String sortBy, String sortDir);
}
