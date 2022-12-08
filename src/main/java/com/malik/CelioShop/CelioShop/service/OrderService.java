package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.CheckoutDto;

public interface OrderService {

    public void placeOrder(CheckoutDto checkoutDto);
}
