package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.product.ProductCategoryDto;

import java.util.List;

public interface ProductCategoryService {
    ProductCategoryDto getCategoryById(Long categoryId);

    ProductCategoryDto createCategory(ProductCategoryDto productCategoryDto);

    List<ProductCategoryDto> getAllCategories();

    void deleteCategoryById(Long categoryId);

    ProductCategoryDto updateProductById(Long categoryId, ProductCategoryDto productCategoryDto);



}
