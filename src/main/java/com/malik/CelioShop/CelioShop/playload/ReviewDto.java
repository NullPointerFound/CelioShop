package com.malik.CelioShop.CelioShop.playload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDto {

    private Long id;
    private String headline;
    private String comment;
    private Integer rate;
    private Long productId;
    private Long userId;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;

}
