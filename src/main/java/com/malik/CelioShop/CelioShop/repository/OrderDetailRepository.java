package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.order.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}
