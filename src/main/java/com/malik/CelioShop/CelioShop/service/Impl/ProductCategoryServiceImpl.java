package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.Utils.Mapper;
import com.malik.CelioShop.CelioShop.entity.Product;
import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductCategoryDto;
import com.malik.CelioShop.CelioShop.repository.ProductCategoryRepository;
import com.malik.CelioShop.CelioShop.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategoryDto getProductCategoryById(Long categoryId) {
        ProductCategory productCategory = productCategoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("No category Found")
        );


        return Mapper.mapToProductCategoryDto(productCategory);
    }


    // it's used by product Controller
    @Override
    public ProductCategory findProductCategoryById(Long categoryId) {

        //FOR LOCAL USE

        ProductCategory productCategory = productCategoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("No category Found")
        );
        return productCategory;
    }

    @Override
    public ProductCategoryDto createCategory(ProductCategoryDto productCategoryDto) {

        ProductCategory newCategory = Mapper.mapToProductCategory(productCategoryDto); //create id


        ProductCategory savedCategory = productCategoryRepository.save(newCategory);

        return Mapper.mapToProductCategoryDto(savedCategory);
    }

    @Override
    public List<ProductCategoryDto> getAllCategories() {

        List<ProductCategory> categories = productCategoryRepository.findAll();

        List<ProductCategoryDto> categoriesDto = categories.stream().map( category -> Mapper.mapToProductCategoryDto(category)).collect(Collectors.toList());

        return categoriesDto;
    }

    @Override
    public void deleteCategoryById(Long categoryId) {

        if ( productCategoryRepository.existsById(categoryId)){ productCategoryRepository.deleteById(categoryId);}
        else
            throw new RuntimeException("No category was found");
    }

    @Override
    public void updateProductById(Long categoryId) {

    }
}
