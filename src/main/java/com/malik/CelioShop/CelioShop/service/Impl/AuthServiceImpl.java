package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.user.Role;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.ResourceAlreadyExist;
import com.malik.CelioShop.CelioShop.playload.RegisterDto;
import com.malik.CelioShop.CelioShop.playload.SignDto;
import com.malik.CelioShop.CelioShop.repository.RoleRepository;
import com.malik.CelioShop.CelioShop.repository.UserRepository;
import com.malik.CelioShop.CelioShop.security.JwtTokenProvider;
import com.malik.CelioShop.CelioShop.service.AuthService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void registerUser(RegisterDto userDto){


        if (userRepository.existsByUsername(userDto.getUsername())){
            throw new ResourceAlreadyExist("User","Username",userDto.getUsername());
        }

        // Check for email if exists in a DB
        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new ResourceAlreadyExist("User","Email",userDto.getEmail());
        }

        User newUser = modelMapper.map(userDto, User.class);
        Role role = roleRepository.findByName("ROLE_USER");
        newUser.setRole(Collections.singleton(role));
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(newUser);

    }

    @Override
    public String login(SignDto signDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signDto.getUsernameOrEmail(), signDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

}
