package com.malik.CelioShop.CelioShop.playload;

import com.malik.CelioShop.CelioShop.entity.user.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDtoResponse {
    private Long id;

    private String headline;

    private String comment;

    private Integer rate;
    private Long productId;
    private UserDtoResponse user;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
}
