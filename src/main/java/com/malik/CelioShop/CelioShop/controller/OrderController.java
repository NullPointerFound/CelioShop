package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.entity.order.Order;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.playload.CheckoutDto;
import com.malik.CelioShop.CelioShop.service.OrderService;
import com.malik.CelioShop.CelioShop.service.ServiceHelper;
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

    @PostMapping("/checkout")
    public ResponseEntity<String> placeOrder(@RequestBody CheckoutDto checkoutDto){
        orderService.placeOrder(checkoutDto);
        return new ResponseEntity<>("Order has been placed successfully", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getUserOrders(){

        return new ResponseEntity<>(orderService.getUserOrders(), HttpStatus.OK);
    }

}
