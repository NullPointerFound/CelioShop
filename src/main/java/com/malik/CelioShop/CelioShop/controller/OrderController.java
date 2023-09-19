package com.malik.CelioShop.CelioShop.controller;


import com.malik.CelioShop.CelioShop.entity.order.Orders;
import com.malik.CelioShop.CelioShop.playload.CheckoutDto;
import com.malik.CelioShop.CelioShop.service.OrderService;
import com.malik.CelioShop.CelioShop.service.ServiceHelper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private OrderService orderService;

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PostMapping("/checkout")
    public ResponseEntity<String> placeOrder(@RequestBody CheckoutDto checkoutDto){
        orderService.placeOrder(checkoutDto);
        return new ResponseEntity<>("Order has been placed successfully", HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @GetMapping
    public ResponseEntity<List<Orders>> getUserOrders(){

        return new ResponseEntity<>(orderService.getUserOrders(), HttpStatus.OK);
    }

}
