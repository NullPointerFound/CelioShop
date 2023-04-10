package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.Cart;
import com.malik.CelioShop.CelioShop.entity.order.Order;
import com.malik.CelioShop.CelioShop.entity.order.OrderDetail;
import com.malik.CelioShop.CelioShop.entity.order.OrderStatus;
import com.malik.CelioShop.CelioShop.entity.order.PaymentMethod;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.CelioShopApiException;
import com.malik.CelioShop.CelioShop.playload.CartItem;
import com.malik.CelioShop.CelioShop.playload.CheckoutDto;
import com.malik.CelioShop.CelioShop.repository.OrderDetailRepository;
import com.malik.CelioShop.CelioShop.repository.OrderRepository;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.service.CartService;
import com.malik.CelioShop.CelioShop.service.OrderService;
import com.malik.CelioShop.CelioShop.service.ServiceHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private CartService cartService;
    private ServiceHelper serviceHelper;

    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    private ProductRepository productRepository;

    @Override
    @Transactional
    public void placeOrder(CheckoutDto checkoutDto) {
        User user = serviceHelper.getAuthenticatedUser();

        CartItem cart = cartService.getCartOfAuthenticatedUser();

        List<Cart> cartItems = cart.getItems();

        cartItems.stream().forEach( (item) ->
        {
            if (item.getQuantity() > item.getProduct().getQuantity()) {
                throw new CelioShopApiException("there is not enough quantity", HttpStatus.BAD_REQUEST);
            }
        });

        Order newOrder = new Order();
        newOrder.setAddress(checkoutDto.getAddress());
        newOrder.setPhoneNumber(checkoutDto.getPhoneNumber());
        newOrder.setOrderStatus(OrderStatus.PROCESSING);
        newOrder.setPaymentMethod(PaymentMethod.CASH_ON_DELIVERY);
        newOrder.setSubtotal(cart.getTotalPrice());
        newOrder.setUser(user);
        orderRepository.save(newOrder);

        for ( Cart item : cartItems){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setUnitPrice(item.getPrice());
            orderDetail.setOrder(newOrder);
            orderDetail.setProduct(item.getProduct());
            orderDetailRepository.save(orderDetail);
            int remainingQuantity = item.getProduct().getQuantity() - item.getQuantity();
            productRepository.updateProductQuantity(item.getProduct().getId(),remainingQuantity);
        }

    }

    @Override
    public List<Order> getUserOrders() {

        User authenticatedUser = serviceHelper.getAuthenticatedUser();

        List<Order> orderList = orderRepository.findByUser(authenticatedUser);

        return (orderList != null) ? orderList : List.of();
    }
}
