package com.malik.CelioShop.CelioShop.playload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PageDto {


    private Long id;

    @NotEmpty
    @NotBlank
    @Size(min = 4, max = 100)
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private Integer priority;

}
