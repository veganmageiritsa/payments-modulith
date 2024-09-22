package com.nl.paymentsmodulith.orders.application.ports.out;

import java.math.BigDecimal;
import java.util.List;

import com.nl.paymentsmodulith.orders.domain.model.Order;
import com.nl.paymentsmodulith.orders.domain.model.OrderInventory;

public interface OrderDatabase {
    
    Order save(Order order);
    
    List<OrderInventory> saveOrderInventories(List<OrderInventory> orderInventories);
    

    Order getOrderById(Long orderIdentifier);
    
    
    BigDecimal orderIdAmount(Long orderId);
    
}
