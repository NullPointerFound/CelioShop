package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.RegisterDto;
import com.malik.CelioShop.CelioShop.playload.SignDto;

public interface AuthService {

    public void registerUser(RegisterDto userDto);

    public String login(SignDto signDto);
}
