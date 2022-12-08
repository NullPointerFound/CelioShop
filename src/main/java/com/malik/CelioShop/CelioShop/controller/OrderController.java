package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.CheckoutDto;
import com.malik.CelioShop.CelioShop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {

    private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<String> placeOrder(@RequestBody CheckoutDto checkoutDto){
        orderService.placeOrder(checkoutDto);
        return new ResponseEntity<>("Order has been placed successfully", HttpStatus.OK);
    }

}
