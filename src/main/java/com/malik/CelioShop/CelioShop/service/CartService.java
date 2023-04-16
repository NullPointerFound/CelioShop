package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.playload.CartItem;

public interface CartService {

    public void addProductToCart(Long productId, Integer id);

    public CartItem getCartOfAuthenticatedUser();

    void updateCartProduct (Long productId, Integer quantity);

    void deleteCartProduct(Long productId);

    public void deleteCartByUser(User user);
}
