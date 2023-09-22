package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.service.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class ProductMediaController {

    private MediaService mediaService;

    @PostMapping("{productId}/media")
    public ResponseEntity<String> uploadMedia(@RequestParam("image") MultipartFile file, @PathVariable Long productId) throws IOException {

        return new ResponseEntity<>(mediaService.uploadMedia(file,productId), HttpStatus.CREATED);
    }

    @GetMapping("/media/{mediaName}")
    public ResponseEntity<byte[]> getMediaByName(@PathVariable String mediaName) throws IOException {

        byte[] media = mediaService.downloadMedia(mediaName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(media);
    }


}
