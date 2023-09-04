package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.Page;
import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.playload.PageDto;
import com.malik.CelioShop.CelioShop.playload.ProductDtoResponse;
import com.malik.CelioShop.CelioShop.repository.PageRepository;
import com.malik.CelioShop.CelioShop.service.PageService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PageServiceImpl implements PageService {

    private PageRepository pageRepository;

    private ModelMapper modelMapper;

    @Override
    public PageDto createPage(PageDto pageDto) {

        Page pageSaved =  pageRepository.save(modelMapper.map(pageDto,Page.class));
        PageDto pageDtoResponse = modelMapper.map(pageSaved, PageDto.class);
        return pageDtoResponse;
    }

    @Override
    public PageDto getPageById(Long pageId) {

        // Retrieve the page by ID and if it doesn't exist we throw a Resource not found exception
        Page page = pageRepository.findById(pageId).orElseThrow(
                ()-> new ResourceNotFound("Page","ID",pageId)
        );

        // we return the page found after we convert it to DTO
        return modelMapper.map(page, PageDto.class);


    }

    @Override
    public List<PageDto> getAllPages() {
        return pageRepository.findAll().stream().map( (page)-> modelMapper.map(page,PageDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deletePageById(Long pageId) {
        // Retrieve the page by ID and if it doesn't exist we throw a Resource not found exception
        Page page = pageRepository.findById(pageId).orElseThrow(
                ()-> new ResourceNotFound("Page","ID",pageId)
        );

        // Delete the product
        pageRepository.delete(page);

    }

    @Override
    public PageDto updatePageById(Long pageId, PageDto pageDto) {

        Page page = pageRepository.findById(pageId).orElseThrow(
                ()-> new ResourceNotFound("Page","ID",pageId)
        );

        if (pageDto.getTitle() != null){
            page.setTitle(pageDto.getTitle());
        }

        if (pageDto.getContent() != null){
            page.setContent(pageDto.getContent());
        }

        if (pageDto.getPriority() != null){
            page.setPriority(pageDto.getPriority());
        }

        return modelMapper.map(pageRepository.save(page), PageDto.class);

    }
}
