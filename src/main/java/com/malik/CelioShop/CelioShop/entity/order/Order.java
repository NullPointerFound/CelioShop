package com.malik.CelioShop.CelioShop.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.malik.CelioShop.CelioShop.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


/*
    This class for Order entity,
    It has a many-to-one relationship with user entity,
    and one-to-many relationship with OrderDetail entity,
    Only authenticated user can create an order and see their previous order,
    while the admin can see all the orders created, and mark them shipped.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client_address")
    private String address;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private BigDecimal subtotal;

    @CreationTimestamp
    private LocalDateTime orderDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetailSet;

}
