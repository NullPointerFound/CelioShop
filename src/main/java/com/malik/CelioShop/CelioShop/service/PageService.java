package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.PageDto;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.playload.ProductDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageService {


    PageDto createPage(PageDto pageDto);

    PageDto getPageById(Long pageId);

    List<PageDto> getAllPages();

    void deletePageById(Long pageId);

    PageDto updatePageById(Long productId, PageDto pageDto);

}
