package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.entity.product.ProductMedia;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/media")
public class ProductMediaController {

    @PostMapping
    public ResponseEntity<ProductMedia> uploadMedia(@RequestParam("image") MultipartFile file) throws IOException {
        String
    }


}
