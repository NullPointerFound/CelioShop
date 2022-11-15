package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.ProductMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductMediaRepository extends JpaRepository<ProductMedia,Long> {
    Optional<ProductMedia> findByName(String fileName);
}
