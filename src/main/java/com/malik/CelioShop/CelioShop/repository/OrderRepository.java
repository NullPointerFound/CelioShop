package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.order.Order;
import com.malik.CelioShop.CelioShop.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}
