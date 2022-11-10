package com.malik.CelioShop.CelioShop.Utils;

import com.malik.CelioShop.CelioShop.entity.Product;
import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductCategoryDto;
import com.malik.CelioShop.CelioShop.playload.ProductDto;

import java.util.stream.Collectors;

public class Mapper {

    // method to map from Product Entity to Product DTO
    public static ProductDto mapToProductDto(Product product){
        if(product != null){
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setSku(product.getSku());
            productDto.setPrice(product.getPrice());
            productDto.setQuantity(product.getQuantity());
            productDto.setImgUrl(product.getImgUrl());
            if(product.getProductCategory() != null){
                productDto.setProductCategoryId(product.getProductCategory().getId());
            }

            productDto.setCreationDate(product.getCreationDate());
            productDto.setUpdateDate(product.getUpdateDate());
            return productDto;
        }
        return null;

    }

    // method to map from Product DTO to Product Entity
    public static Product mapToProduct(ProductDto productDto){

        if(productDto != null){
            Product product = new Product();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setSku(productDto.getSku());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());
            product.setImgUrl(productDto.getImgUrl());
            return product;
        }
        return null;
    }


    // method to map from ProductCategory DTO to ProductCategory Entity
    public static ProductCategory mapToProductCategory(ProductCategoryDto productCategoryDto){
        if ( productCategoryDto != null ){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setName(productCategoryDto.getName());
            productCategory.setDescription(productCategoryDto.getDescription());
            return productCategory;
        }
        return null;
    }

    // method to map from ProductCategory Entity to ProductCategory Dto
    public static ProductCategoryDto mapToProductCategoryDto(ProductCategory productCategory){
        if ( productCategory != null){
            ProductCategoryDto productCategoryDto = new ProductCategoryDto();
            productCategoryDto.setId(productCategory.getId());
            productCategoryDto.setName(productCategory.getName());
            productCategoryDto.setDescription(productCategory.getDescription());

            if ( productCategory.getProductSet() != null )
                productCategoryDto.setProductSet(
                        productCategory
                                .getProductSet()
                                .stream()
                                .map( product -> mapToProductDto(product)).collect(Collectors.toSet()));

            return productCategoryDto;
        }
        return null;

    }

}
