package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page,Long> {
}
