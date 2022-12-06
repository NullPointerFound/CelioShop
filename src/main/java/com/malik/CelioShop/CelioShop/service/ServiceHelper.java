package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ServiceHelper {

    private UserRepository userRepository;

    // find the authenticated user
    public User getAuthenticatedUser(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(
                ()->  new ResourceNotFound("User","Username or Email",username)
        );

        return user;
    }
}
