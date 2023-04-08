package com.malik.CelioShop.CelioShop.playload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class RegisterDto {

    private Long id;
    @NotEmpty(message = "Name may not be empty")
    private String fullName;

    @NotEmpty(message = "email may not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Username may not be empty")
    @Size(min = 3)
    private String username;

    @NotEmpty(message = "Password may not be empty")
    private String password;
}
