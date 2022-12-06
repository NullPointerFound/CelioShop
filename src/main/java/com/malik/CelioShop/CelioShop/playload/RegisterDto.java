package com.malik.CelioShop.CelioShop.playload;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
public class RegisterDto {

    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String password;
}
