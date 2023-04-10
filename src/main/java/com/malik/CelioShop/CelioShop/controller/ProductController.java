package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.playload.ProductDtoResponse;
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
    public ResponseEntity<ProductDtoResponse> createProduct(@Valid @RequestBody ProductDto productDto,
                                                                     @RequestParam(name = "categoryName", required = false) String categoryName) {

        return new ResponseEntity<>(productService.createProduct(productDto,categoryName), HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable Long productId){
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDtoResponse>> getProducts(){

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
    public ResponseEntity<ProductDtoResponse> updateProductById(@PathVariable Long productId, @RequestBody ProductDto updatedProductDto){

        return new ResponseEntity<>(productService.updateProductById(productId, updatedProductDto), HttpStatus.OK);
    }

    @GetMapping("/product/category/{categoryId}")
    public ResponseEntity<List<ProductDtoResponse>> getProductsByCategoryId(@PathVariable(value = "categoryId") Long categoryId){

        return new ResponseEntity<>(productService.getProductsByCategoryId(categoryId),HttpStatus.OK);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductDtoResponse>> searchProducts(@RequestParam(name = "keyword", required = true) String query){

        return ResponseEntity.ok(productService.searchProduct(query));
    }
}
