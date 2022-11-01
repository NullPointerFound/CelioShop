package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductCategoryDto;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class productCategoryController {

    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategoryDto> createCategory(@RequestBody ProductCategoryDto productCategoryDto){

        return new ResponseEntity<>(productCategoryService.createCategory(productCategoryDto), HttpStatus.CREATED);
    }

    @GetMapping("{/id}")
    public ResponseEntity<ProductCategoryDto> createCategory(@PathVariable Long categoryId){

        return new ResponseEntity<>(productCategoryService.getProductCategoryById(categoryId), HttpStatus.OK);
    }

    @GetMapping
    public List<ProductCategoryDto> getAllCategories(){

        return productCategoryService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long categoryId){
        productCategoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok("The category has been deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCtegoryById(@PathVariable Long categoryId, @RequestBody ProductCategoryDto productCategoryDto){

        productCategoryService.updateProductById(categoryId);
        return ResponseEntity.ok("Category with ID : %s has been updated successfully");
    }


}
