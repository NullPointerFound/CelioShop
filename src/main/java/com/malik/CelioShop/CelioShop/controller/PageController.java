package com.malik.CelioShop.CelioShop.controller;


import com.malik.CelioShop.CelioShop.entity.Page;
import com.malik.CelioShop.CelioShop.playload.PageDto;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.playload.ProductDtoResponse;
import com.malik.CelioShop.CelioShop.service.PageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/page")
public class PageController {

    private PageService pageService;


    public ResponseEntity<PageDto> createPage(@Valid @RequestBody PageDto pageDto) {
        return new ResponseEntity<>(pageService.createPage(pageDto), HttpStatus.CREATED);
    }

    public ResponseEntity<String> getAllPages(@Valid @RequestBody PageDto pageDto) {
        return new ResponseEntity<>(pageService.getAllPages(pageDto), HttpStatus.OK);
    }

    public ResponseEntity<PageDto> updatePage(@RequestBody PageDto pageDto,@PathVariable Long pageId) {
        return new ResponseEntity<>(pageService.updatePage(pageDto), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deletePagePage(@PathVariable Long pageId) {
        return
    }



}
