package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.JWTAuthResponse;
import com.malik.CelioShop.CelioShop.playload.RegisterDto;
import com.malik.CelioShop.CelioShop.playload.SignDto;
import com.malik.CelioShop.CelioShop.security.JwtTokenProvider;
import com.malik.CelioShop.CelioShop.service.AuthService;
import com.malik.CelioShop.CelioShop.service.Impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "Authentication"
)
public class AuthController {

    private AuthServiceImpl registerService;


    private AuthService authService;

    @Operation(
            summary = "Register a new user Endpoint",
            description = "Create a new user"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"
    )
    @PostMapping("/register")
    public ResponseEntity<String> registerUser( @RequestBody @Valid RegisterDto user){

        registerService.registerUser(user);

        log.info(String.format("registerUser new user"));

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody @Valid SignDto signDto){

        String token = authService.login(signDto);

        log.info(String.format("signIn a user signIn"));

        return ResponseEntity.ok(new JWTAuthResponse(token));

    }
}
