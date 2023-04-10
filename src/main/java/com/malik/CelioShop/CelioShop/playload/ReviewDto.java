package com.malik.CelioShop.CelioShop.playload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDto {

    private Long id;
    @NotEmpty
    private String headline;
    @NotEmpty
    private String comment;
    @Min(value = 0, message = "rate can't be less than 0")
    @Max(value = 5, message = "rate can't be more than 5")
    private Integer rate;
    private Long productId;
    private Long userId;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;

}
