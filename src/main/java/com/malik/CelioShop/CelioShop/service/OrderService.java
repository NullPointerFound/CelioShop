package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.entity.order.Order;
import com.malik.CelioShop.CelioShop.playload.CheckoutDto;

import java.util.List;

public interface OrderService {

    public void placeOrder(CheckoutDto checkoutDto);

    List<Order> getUserOrders();
}
