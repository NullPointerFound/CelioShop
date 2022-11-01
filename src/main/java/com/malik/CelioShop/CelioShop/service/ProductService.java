package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDto addProduct(ProductDto productDto, ProductCategory productCategory);

    ProductDto getProductById(Long productId);

    List<ProductDto> getAllProduct();

    void deleteProductById(Long productId);

    void updateProductById(Long productId);
}
