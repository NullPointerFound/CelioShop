package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.playload.JWTAuthResponse;
import com.malik.CelioShop.CelioShop.playload.RegisterDto;
import com.malik.CelioShop.CelioShop.playload.SignDto;
import com.malik.CelioShop.CelioShop.security.JwtTokenProvider;
import com.malik.CelioShop.CelioShop.service.Impl.RegisterServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private RegisterServiceImpl registerService;
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto user){
        registerService.registerUser(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignDto signDto){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signDto.getUsernameOrEmail(),
                signDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get token from tokeProvider
        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));

    }
}
