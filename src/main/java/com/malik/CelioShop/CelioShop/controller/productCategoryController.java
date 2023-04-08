package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.ProductCategoryDto;
import com.malik.CelioShop.CelioShop.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class productCategoryController {

    private ProductCategoryService productCategoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductCategoryDto> createCategory(@RequestBody ProductCategoryDto productCategoryDto){

        return new ResponseEntity<>(productCategoryService.createCategory(productCategoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ProductCategoryDto> getCategoryById(@PathVariable(value = "categoryId") Long categoryId){

        return new ResponseEntity<>(productCategoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDto>> getAllCategories(){

        return new ResponseEntity<>(productCategoryService.getAllCategories(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long categoryId){
        productCategoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok("The category has been deleted successfully");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<String> updateCategoryById(@PathVariable(value = "categoryId") Long categoryId, @RequestBody ProductCategoryDto productCategoryDto){

        productCategoryService.updateProductById(categoryId);
        return ResponseEntity.ok("Category with ID : %s has been updated successfully");
    }
}
