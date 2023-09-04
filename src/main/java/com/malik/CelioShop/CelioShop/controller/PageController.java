package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.PageDto;

import com.malik.CelioShop.CelioShop.service.PageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PageController {
    private PageService pageService;

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/page")
    public ResponseEntity<PageDto> createPage(@Valid @RequestBody PageDto pageDto) {
        return new ResponseEntity<>(pageService.createPage(pageDto), HttpStatus.CREATED);
    }

    @GetMapping("/pages")
    public ResponseEntity<List<PageDto>> getAllPages() {
        return new ResponseEntity<>(pageService.getAllPages(), HttpStatus.OK);
    }


    @GetMapping("/page/{pageId}")
    public ResponseEntity<PageDto> getPageById(@PathVariable Long pageId) {
        return new ResponseEntity<>(pageService.getPageById(pageId), HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/page/{pageId}")
    public ResponseEntity<PageDto> updatePageById(@RequestBody PageDto pageDto,@PathVariable Long pageId) {
        return new ResponseEntity<>(pageService.updatePageById(pageId,pageDto), HttpStatus.CREATED);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/page/{pageId}")
    public ResponseEntity<String> deletePagePageById(@PathVariable Long pageId) {
        pageService.deletePageById(pageId);
        return ResponseEntity.ok(String.format("Page with ID : %s has been deleted successfully",pageId));
    }



}
