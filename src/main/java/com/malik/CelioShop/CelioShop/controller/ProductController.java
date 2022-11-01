package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.entity.ProductCategory;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.service.Impl.ProductServiceImpl;
import com.malik.CelioShop.CelioShop.service.ProductCategoryService;
import com.malik.CelioShop.CelioShop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private ProductService productService;
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        ProductCategory productCategory = productCategoryService.getProductCategoryById(productDto.getProductCategoryId());
        return new ResponseEntity<>(productService.addProduct(productDto,productCategory), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping
    public List<ProductDto> getProductById(){
        return productService.getAllProduct();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId){
        productService.deleteProductById(productId);
        return ResponseEntity.ok("Product with %s has been delete successfully"+productId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(@PathVariable Long productId, @RequestBody ProductDto updatedProductDto){

        productService.updateProductById(productId);
        return ResponseEntity.ok("Product with %s has been updated successfully");
    }
    

}
