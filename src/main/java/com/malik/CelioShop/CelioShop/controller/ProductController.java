package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.product.PageProductDtoResponse;
import com.malik.CelioShop.CelioShop.playload.product.ProductDto;
import com.malik.CelioShop.CelioShop.playload.product.ProductDtoResponse;
import com.malik.CelioShop.CelioShop.service.ProductService;
import com.malik.CelioShop.CelioShop.utils.AppConstants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")

public class ProductController {

    private ProductService productService;

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/product")
    public ResponseEntity<ProductDtoResponse> createProduct(@Valid @RequestBody ProductDto productDto,
                                                            @RequestParam(name = "categoryName", required = false) String categoryName) {

        return new ResponseEntity<>(productService.createProduct(productDto, categoryName), HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<PageProductDtoResponse> getAllProducts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {

        return new ResponseEntity<>(productService.getAllProducts(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok(String.format("Product with ID : %s has been deleted successfully", productId));
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDtoResponse> updateProductById(@PathVariable Long productId, @RequestBody ProductDto updatedProductDto) {

        return new ResponseEntity<>(productService.updateProductById(productId, updatedProductDto), HttpStatus.OK);
    }

    @GetMapping("/product/category/{categoryId}")
    public ResponseEntity<List<ProductDtoResponse>> getProductsByCategoryId(@PathVariable(value = "categoryId") Long categoryId,
                                                                            @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                                                            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                                            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                                            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {

        return new ResponseEntity<>(productService.getProductsByCategoryId(categoryId, pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/product/search")
    public ResponseEntity<PageProductDtoResponse> searchProducts(@RequestParam(name = "keyword", required = true) String query,
                                                                   @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                                                   @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                                   @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                                   @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        return ResponseEntity.ok(productService.searchProduct(query, pageNumber, pageSize, sortBy, sortDir));
    }
}
