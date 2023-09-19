package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
