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

    @Query("UPDATE Product p SET "+
            "p.quantity = :remainingQuantity "+
            "WHERE p.id = :productId")
    @Modifying
    void updateProductQuantity(Long productId, int remainingQuantity);

    @Query("SELECT " +
            "P.name AS product_name," +
            "OD.quantity AS quantity_sold," +
            "O.orderDate AS sale_date" + // Add a space here
            " FROM" +
            " Product AS P" +
            " INNER JOIN" +
            " OrderDetail AS OD ON P.id = OD.product" +
            " INNER JOIN" +
            " Orders AS O ON OD.order = O.id")
    List<Object[]> findProductsSold();
}
