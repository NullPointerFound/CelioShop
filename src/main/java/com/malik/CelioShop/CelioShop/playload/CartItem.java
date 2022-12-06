package com.malik.CelioShop.CelioShop.playload;

import com.malik.CelioShop.CelioShop.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {

    private List<Cart> items;

    private BigDecimal totalPrice;
}
