package com.malik.CelioShop.CelioShop.playload;

import com.malik.CelioShop.CelioShop.entity.order.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutDto {

    private String address;

    private String phoneNumber;

    private PaymentMethod paymentMethod;

}
