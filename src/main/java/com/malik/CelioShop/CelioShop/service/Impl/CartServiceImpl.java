package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.Cart;
import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.playload.CartItem;
import com.malik.CelioShop.CelioShop.repository.CartRepository;
import com.malik.CelioShop.CelioShop.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static com.malik.CelioShop.CelioShop.utils.AppConstants.DEFAULT_SHIPPING_PRICE;
import static com.malik.CelioShop.CelioShop.utils.AppConstants.DEFAULT_TAX_PERCENTAGE;


@Slf4j
@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ServiceHelper serviceHelper;
    

    @Override
    public void addProductToCart(Long productId, Integer quantity){

        User authenticatedUser = serviceHelper.getAuthenticatedUser();

        Product product = serviceHelper.getProductByIdOrThrowNotFoundException(productId);

        Cart cart = cartRepository.findByUserAndProduct(productId, authenticatedUser.getId());//authenticatedUser.getId());

        if (cart != null){
            Integer updatedQuantity = cart.getQuantity() + quantity;
            serviceHelper.checkQuantity(product, updatedQuantity);
            cart.setQuantity(updatedQuantity);
            BigDecimal Price = cart.getPrice().add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            cart.setPrice(Price);
        } else {
            serviceHelper.checkQuantity(product, quantity);
            cart = new Cart();
            cart.setProduct(product);
            cart.setUser(authenticatedUser);
            cart.setQuantity(quantity);
            BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            cart.setPrice(price);
        }
        cartRepository.save(cart);
    }


    @Override
    public CartItem getCartOfAuthenticatedUser(){
        User authenticatedUser = serviceHelper.getAuthenticatedUser();
        CartItem cartItem = new CartItem();
        List<Cart> cartItems = cartRepository.findByUser(authenticatedUser);

        if (cartItems != null){
            BigDecimal subTotalPrice = BigDecimal.ZERO;

            for (Cart item : cartItems){
                subTotalPrice = subTotalPrice.add(item.getPrice());
            }
            cartItem.setItems(cartItems);
            cartItem.setShippingCost(DEFAULT_SHIPPING_PRICE);
            cartItem.setTaxPercentage(DEFAULT_TAX_PERCENTAGE);
            cartItem.setSubTotal(subTotalPrice);
            BigDecimal taxPaid = (cartItem.getSubTotal().multiply(cartItem.getTaxPercentage())).divide(BigDecimal.valueOf(100));
            cartItem.setTaxCost(taxPaid);
            cartItem.setTotalPrice(subTotalPrice.add(cartItem.getShippingCost()).add(taxPaid));

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

    @Transactional
    @Override
    public void deleteCartByUser(User user) {
        List<Cart> cartItems = cartRepository.findByUser(user);
        if( cartItems == null ){
            throw new ResourceNotFound("Cart","user",user.getId());
        }
        for( Cart item: cartItems){
            cartRepository.deleteById(item.getId());
        }
    }

}
