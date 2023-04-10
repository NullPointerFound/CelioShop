package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.Cart;
import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.CelioShopApiException;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.playload.CartItem;
import com.malik.CelioShop.CelioShop.repository.CartRepository;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.service.CartService;
import com.malik.CelioShop.CelioShop.service.ServiceHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


@Slf4j
@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ServiceHelper serviceHelper;
    private ProductRepository productRepository;

    @Override
    public void addProductToCart(Long productId, Integer quantity){

        User authenticatedUser = serviceHelper.getAuthenticatedUser();

        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFound("product","ID",productId)
        );

        Cart cart = cartRepository.findByUserAndProduct(productId, authenticatedUser.getId());//authenticatedUser.getId());

        if (cart != null){
            Integer updatedQuantity = cart.getQuantity() + quantity;
            checkQuantity(product, updatedQuantity);
            cart.setQuantity(updatedQuantity);
            BigDecimal Price = cart.getPrice().add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            cart.setPrice(Price);
        } else {
            checkQuantity(product, quantity);
            cart = new Cart();
            cart.setProduct(product);
            cart.setUser(authenticatedUser);
            cart.setQuantity(quantity);
            BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            cart.setPrice(price);
        }
        cartRepository.save(cart);
    }

    private void checkQuantity(Product product, Integer quantity){
        if (quantity > product.getQuantity()){
            throw new CelioShopApiException("there is not enough quantity", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public CartItem getCartOfAuthenticatedUser(){
        User authenticatedUser = serviceHelper.getAuthenticatedUser();
        CartItem cartItem = new CartItem();
        List<Cart> cartItems = cartRepository.findByUser(authenticatedUser);

        if (cartItems != null){
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (Cart item : cartItems){
                log.info("malik: "+item);
                totalPrice = totalPrice.add(item.getPrice());
            }
            cartItem.setItems(cartItems);
            cartItem.setTotalPrice(totalPrice);

        }else{
            cartItem.setItems(Collections.emptyList());
            cartItem.setTotalPrice(new BigDecimal(0));
        }
        return cartItem;
    }

    @Override
    public void updateCartProduct(Long productId, Integer quantity) {

        User authenticatedUser = serviceHelper.getAuthenticatedUser();
        Cart cart = cartRepository.findByUserAndProduct(productId, authenticatedUser.getId());//authenticatedUser.getId());
        if( cart == null ){
            throw new ResourceNotFound("Cart","Product",productId);
        }

        cart.setQuantity(quantity);
        cart.setPrice(cart.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity)));
        cartRepository.save(cart);
    }

    @Override
    public void deleteCartProduct(Long productId) {
        User authenticatedUser = serviceHelper.getAuthenticatedUser();
        Cart cart = cartRepository.findByUserAndProduct(productId, authenticatedUser.getId());//authenticatedUser.getId());
        if( cart == null ){
            throw new ResourceNotFound("Cart","Product",productId);
        }

        cartRepository.delete(cart);
    }

}
