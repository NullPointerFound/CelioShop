package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.CelioShopApiException;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ServiceHelper {

    private UserRepository userRepository;

    private ProductRepository productRepository;

    // find the authenticated user
    protected User getAuthenticatedUser(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(
                ()->  new ResourceNotFound("User","Username or Email",username)
        );

        return user;
    }

    protected Product getProductByIdOrThrowNotFoundException(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFound("product","ID", productId)
        );
        return product;
    }

    protected void checkQuantity(Product product, Integer quantity){
        if (quantity > product.getQuantity()){
            throw new CelioShopApiException("there is not enough quantity", HttpStatus.BAD_REQUEST);
        }
    }
}
