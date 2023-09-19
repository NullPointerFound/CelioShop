package com.malik.CelioShop.CelioShop.repository;


import com.malik.CelioShop.CelioShop.entity.order.Orders;
import com.malik.CelioShop.CelioShop.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUser(User user);
}
