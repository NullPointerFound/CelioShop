package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.repository.UserRepository;
import org.springframework.http.ResponseEntity;

public interface UserService {




    public ResponseEntity<?> registerUser(User user);

}
