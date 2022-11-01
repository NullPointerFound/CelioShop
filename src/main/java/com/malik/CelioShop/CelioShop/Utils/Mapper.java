package com.malik.CelioShop.CelioShop.Utils;

import com.malik.CelioShop.CelioShop.entity.Product;
import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductCategoryDto;
import com.malik.CelioShop.CelioShop.playload.ProductDto;

public class Mapper {

    // method to map from Product Entity to Product DTO
    public static ProductDto mapToProductDto(Product product){

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
    public static Product mapToProduct(ProductDto productDto, ProductCategory productCategory){
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


    // method to map from ProductCategory DTO to ProductCategory Entity
    public static ProductCategory mapToProductCategory(ProductCategoryDto productCategoryDto){

        ProductCategory productCategory = new ProductCategory();

        productCategory.setName(productCategoryDto.getName());
        productCategory.setDescription(productCategoryDto.getDescription());

        return productCategory;
    }

    // method to map from ProductCategory Entity to ProductCategory Dto
    public static ProductCategoryDto mapToProductCategoryDto(ProductCategory productCategory){

        ProductCategoryDto productCategoryDto = new ProductCategoryDto();

        productCategoryDto.setId(productCategory.getId());
        productCategoryDto.setName(productCategory.getName());
        productCategoryDto.setDescription(productCategory.getDescription());

        return productCategoryDto;
    }

}
