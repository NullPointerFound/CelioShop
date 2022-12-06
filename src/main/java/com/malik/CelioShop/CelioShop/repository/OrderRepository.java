package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
