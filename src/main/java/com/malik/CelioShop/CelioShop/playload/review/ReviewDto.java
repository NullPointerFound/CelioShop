package com.malik.CelioShop.CelioShop.playload.review;

import com.malik.CelioShop.CelioShop.entity.review.ReviewVote;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

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
    private Set<ReviewVote> reviewVote;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
    private Long vote;

}
