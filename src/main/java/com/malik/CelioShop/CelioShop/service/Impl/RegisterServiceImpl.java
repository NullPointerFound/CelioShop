package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.user.Role;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.ResourceAlreadyExist;
import com.malik.CelioShop.CelioShop.playload.RegisterDto;
import com.malik.CelioShop.CelioShop.repository.RoleRepository;
import com.malik.CelioShop.CelioShop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class RegisterServiceImpl {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public void registerUser(RegisterDto userDto){


        if (userRepository.existsByUsername(userDto.getUsername())){
            throw new ResourceAlreadyExist("User","Username",userDto.getUsername());
        }

        // Check for email if exists in a DB
        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new ResourceAlreadyExist("User","Email",userDto.getEmail());
        }

        User newUser = modelMapper.map(userDto, User.class);
        Role role = roleRepository.findByName("ROLE_ADMIN");
        newUser.setRole(Collections.singleton(role));
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(newUser);


    }

}
