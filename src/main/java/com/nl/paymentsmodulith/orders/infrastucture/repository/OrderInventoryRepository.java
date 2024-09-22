package com.nl.paymentsmodulith.orders.infrastucture.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nl.paymentsmodulith.orders.domain.model.OrderInventory;

@Repository
 public interface OrderInventoryRepository extends JpaRepository<OrderInventory, Long> {
    
    @Query(nativeQuery = true, value = "SELECT SUM(oi.total_price) from order_inventory oi " +
                                       "where oi.order_id = ?1")
    BigDecimal orderIdAmount(long orderId);
    
}

