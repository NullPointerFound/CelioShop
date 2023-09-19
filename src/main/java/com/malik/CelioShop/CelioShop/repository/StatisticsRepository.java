package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatisticsRepository extends JpaRepository<Product,Long> {

    @Query("SELECT " +
            "p.id AS product_id, " +
            "p.name AS product_name, " +
            "p.cost AS product_cost," +
            "p.sku AS product_sku," +
            "p.quantity AS product_quantity, " +
            "p.rateCount AS product_rate_count, " +
            "od.quantity AS quantity_sold, " +
            "od.saleDate, " +
            "od.subtotal, " +
            "od.id " +
            "FROM " +
            "Product p " +
            "JOIN " +
            "OrderDetail od ON p.id = od.product " +
            "ORDER BY " +
            "od.saleDate DESC")
        List<Object[]> findProductsSold();
}
