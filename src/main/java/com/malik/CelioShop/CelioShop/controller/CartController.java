package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.entity.Cart;
import com.malik.CelioShop.CelioShop.playload.CartItem;
import com.malik.CelioShop.CelioShop.service.CartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PostMapping("{productId}/{quantity}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long productId, @PathVariable Integer quantity){
        cartService.addProductToCart(productId,quantity);
        return new ResponseEntity<>("Product has been added to you cart successfully", HttpStatus.CREATED);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @GetMapping
    public ResponseEntity<CartItem> viewCart(){
        return new ResponseEntity<>(cartService.getCartOfAuthenticatedUser(), HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("{productId}/{quantity}")
    public ResponseEntity<String> updateCart(@PathVariable Long productId,@PathVariable Integer quantity){

        if(quantity>=1){
            cartService.updateCartProduct(productId,quantity);
        }
        else
            cartService.deleteCartProduct(productId);
        return new ResponseEntity<>("You cart has been updated successfully", HttpStatus.CREATED);
    }

}
