package com.malik.CelioShop.CelioShop.playload;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class UserDtoResponse {

    private Long id;

    private String fullName;

    private String email;

    private String username;


    private LocalDateTime joinedDate;
}
