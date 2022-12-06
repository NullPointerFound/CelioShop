package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.service.ProductCategoryService;
import com.malik.CelioShop.CelioShop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProductWithMedia(@RequestBody ProductDto productDto,
                                                             @RequestParam(name = "categoryName", required = false) String categoryName,
                                                             @RequestPart(value = "imageFile", required = false) MultipartFile media) throws IOException {

        return new ResponseEntity<>(productService.createProduct(productDto,categoryName,null), HttpStatus.CREATED);
    }

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadMedia(@RequestPart ProductDto productDto,
//            @RequestPart("imageFile") MultipartFile media) throws IOException {
//
//        System.out.println(media.getOriginalFilename());
//        System.out.println(media.getName());
//        System.out.println(media.getContentType());
//        System.out.println("**************");
//        System.out.println("Malik: "+productDto.getName());
//
//        return new ResponseEntity<String>("File uploaded successfully",HttpStatus.OK);
//    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getProducts(){

        System.out.println("Get product");
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId){
        productService.deleteProductById(productId);
        return ResponseEntity.ok(String.format("Product with ID : %s has been deleted successfully",productId));
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<String> updateProductById(@PathVariable Long productId, @RequestBody ProductDto updatedProductDto){

        productService.updateProductById(productId);
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
