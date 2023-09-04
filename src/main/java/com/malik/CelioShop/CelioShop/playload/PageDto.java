package com.malik.CelioShop.CelioShop.playload;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
public class PageDto {

    private Long id;

    private String Title;

    private String content;

    private Integer priority;

}
