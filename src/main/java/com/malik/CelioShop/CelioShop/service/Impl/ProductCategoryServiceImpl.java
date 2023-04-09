package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.product.ProductCategory;
import com.malik.CelioShop.CelioShop.exception.ResourceAlreadyExist;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.playload.ProductCategoryDto;
import com.malik.CelioShop.CelioShop.repository.ProductCategoryRepository;
import com.malik.CelioShop.CelioShop.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private ProductCategoryRepository productCategoryRepository;
    private ModelMapper modelMapper;

    @Override
    public ProductCategoryDto getCategoryById(Long categoryId) {

        // Retrieve the category in DB by ID and if it doesn't exist we throw exception
        ProductCategory productCategory = productCategoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFound("Category","ID",categoryId)
        );

        // Return object retrieved (Category) after we convert it to DTO
        return modelMapper.map(productCategory,ProductCategoryDto.class);
    }

    @Override
    public ProductCategoryDto createCategory(ProductCategoryDto productCategoryDto) {

        // Convert product category object to DTO
        ProductCategory newCategory = modelMapper.map(productCategoryDto,ProductCategory.class);

        // Check if the category already exist in DB id it does, we throw an exception
        Optional<ProductCategory> category = productCategoryRepository.findByName(newCategory.getName());

        if(category.isPresent()){
            throw new ResourceAlreadyExist("Category","Name",newCategory.getName());
        }

        // we save the category in DB and save the returned object to new object savedCategory
        ProductCategory savedCategory = productCategoryRepository.save(newCategory);

        // we return savedCategory object after we convert it to DTO
        return modelMapper.map(savedCategory,ProductCategoryDto.class);
    }

    @Override
    public List<ProductCategoryDto> getAllCategories() {

        // Retrieve all categories in DB
        List<ProductCategory> categories = productCategoryRepository.findAll();

        // We convert the categories found to DTO
        List<ProductCategoryDto> categoriesDto = categories.stream().map( category -> modelMapper.map(category,ProductCategoryDto.class)).collect(Collectors.toList());

        return categoriesDto;
    }

    @Override
    public void deleteCategoryById(Long categoryId) {

        // Check if the category exist in DB before delete it
        ProductCategory category = productCategoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFound("Category","ID",categoryId)
        );

        Set<Product> productSet = category.getProductSet();
        productSet.stream().forEach( product -> product.setProductCategory(null));

        productCategoryRepository.deleteById(categoryId);

    }

    @Override
    public ProductCategoryDto updateProductById(Long categoryId, ProductCategoryDto productCategoryDto) {
        // Check if the category exist in DB before delete it
        ProductCategory category = productCategoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFound("Category","ID",categoryId)
        );

        if ( productCategoryDto.getName() != null){
            category.setName(productCategoryDto.getName());
        }
        if ( productCategoryDto.getDescription() != null){
            category.setDescription(productCategoryDto.getDescription());
        }

        return modelMapper.map(productCategoryRepository.save(category),ProductCategoryDto.class) ;
    }

}
