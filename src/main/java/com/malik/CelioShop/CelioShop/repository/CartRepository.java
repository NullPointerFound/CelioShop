package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.Cart;
import com.malik.CelioShop.CelioShop.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("SELECT c from Cart c WHERE Product.id =?1 AND User.id=?2")
    Cart findByUserAndProduct(Long productId, Long userId);

    List<Cart> findByUser(User user);
}
