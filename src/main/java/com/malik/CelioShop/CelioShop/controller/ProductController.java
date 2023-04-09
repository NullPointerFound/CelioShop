package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private ProductService productService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProductWithMedia(@Valid @RequestBody ProductDto productDto) {

        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(){

        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId){
        productService.deleteProductById(productId);
        return ResponseEntity.ok(String.format("Product with ID : %s has been deleted successfully",productId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/product/{productId}")
    public ResponseEntity<String> updateProductById(@PathVariable Long productId,@Valid @RequestBody ProductDto updatedProductDto){

        productService.updateProductById(productId, updatedProductDto);
        return ResponseEntity.ok("Product with ID : %s has been updated successfully");
    }

    @GetMapping("/category/{categoryId}/product")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable(value = "categoryId") Long categoryId){

        return new ResponseEntity<>(productService.getProductsByCategoryId(categoryId),HttpStatus.OK);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam(name = "keyword", required = true) String query){

        return ResponseEntity.ok(productService.searchProduct(query));
    }
}
