package com.nl.paymentsmodulith.orders.infrastucture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nl.paymentsmodulith.orders.domain.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    Optional<Order> findByOrderIdentifier(String orderIdentifier);
    
}
