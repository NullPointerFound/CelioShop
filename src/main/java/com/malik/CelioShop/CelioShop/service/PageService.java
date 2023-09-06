package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.PageDto;

import java.util.List;

public interface PageService {


    PageDto createPage(PageDto pageDto);

    PageDto getPageById(Long pageId);

    List<PageDto> getAllPages();

    void deletePageById(Long pageId);

    PageDto updatePageById(Long productId, PageDto pageDto);

}
