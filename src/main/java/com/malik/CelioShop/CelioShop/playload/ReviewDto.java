package com.malik.CelioShop.CelioShop.playload;

import com.malik.CelioShop.CelioShop.entity.Product;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class ReviewDto {

    private Long id;
    private String headline;
    private String comment;
    private Integer rate;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;

}
