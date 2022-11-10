package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductCategoryService {
    ProductCategoryDto getProductCategoryById(Long categoryId);

    ProductCategory findProductCategoryById(Long categoryId);

    ProductCategoryDto createCategory(ProductCategoryDto productCategoryDto);

    List<ProductCategoryDto> getAllCategories();

    void deleteCategoryById(Long categoryId);

    void updateProductById(Long categoryId);


}
