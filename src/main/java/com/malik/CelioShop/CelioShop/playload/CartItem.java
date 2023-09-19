package com.malik.CelioShop.CelioShop.playload;

import com.malik.CelioShop.CelioShop.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import static com.malik.CelioShop.CelioShop.utils.AppConstants.DEFAULT_SHIPPING_PRICE;
import static com.malik.CelioShop.CelioShop.utils.AppConstants.DEFAULT_TAX_PERCENTAGE;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {

    private List<Cart> items;

    private BigDecimal subTotal;

    private BigDecimal shippingCost;

    private BigDecimal taxPercentage;

    private BigDecimal taxCost;

    private BigDecimal totalPrice;


}
