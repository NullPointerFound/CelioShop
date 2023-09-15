package com.malik.CelioShop.CelioShop.playload.review;

import com.malik.CelioShop.CelioShop.playload.product.ProductDtoResponse;
import lombok.Data;

import java.util.List;

@Data
public class PageReviewDtoResponse {

    private List<ReviewDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
