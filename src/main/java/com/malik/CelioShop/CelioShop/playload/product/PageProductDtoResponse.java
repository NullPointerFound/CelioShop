package com.malik.CelioShop.CelioShop.playload.product;

import lombok.Data;

import java.util.List;

@Data
public class PageProductDtoResponse {

    private List<ProductDtoResponse> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
