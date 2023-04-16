package com.malik.CelioShop.CelioShop.playload;

import com.malik.CelioShop.CelioShop.entity.order.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutDto {

    @NotEmpty
    @Size(min = 6, max = 100)
    private String address;

    @NotEmpty
    @Size(min = 9, max = 20)
    private String phoneNumber;

    private PaymentMethod paymentMethod;
}
