package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByProductCategory(ProductCategory productCategory);


    Optional<Product> findBySku(String sku);
//    @Query("SELECT p FROM Product p WHERE " +
//            "p.name LIKE CONCAT('%', :query, '%')"+
//            "OR p.description LIKE CONCAT('%', :query, '%')")
//    @Query("SELECT p FROM Product p WHERE " +
//            "p.name = :query"+
//            " OR p.description = :query")
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:query% OR p.description LIKE %:query%")
    List<Product> searchProduct(String query);

    @Query("UPDATE Product p SET " +
            "p.avgRate = (SELECT AVG(r.rate) FROM Review r WHERE r.product.id = :productId), " +
            "p.rateCount =(SELECT COUNT(r.id) FROM Review r WHERE r.product.id = :productId) " +
            "WHERE p.id = :productId")
    @Modifying
    void updateAvgRateAndRateCount(Long productId);

}
