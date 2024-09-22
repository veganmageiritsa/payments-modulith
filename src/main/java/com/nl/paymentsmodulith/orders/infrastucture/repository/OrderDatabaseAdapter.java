package com.nl.paymentsmodulith.orders.infrastucture.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nl.paymentsmodulith.exception.ModulithException;
import com.nl.paymentsmodulith.orders.application.ports.out.OrderDatabase;
import com.nl.paymentsmodulith.orders.domain.model.Order;
import com.nl.paymentsmodulith.orders.domain.model.OrderInventory;

@Service
@Transactional
public class OrderDatabaseAdapter implements OrderDatabase {
    
    private final OrderRepository orderRepository;
    
    private final OrderInventoryRepository orderInventoryRepository;
    
    public OrderDatabaseAdapter(final OrderRepository orderRepository,
                                final OrderInventoryRepository orderInventoryRepository) {
        this.orderRepository = orderRepository;
        this.orderInventoryRepository = orderInventoryRepository;
    }
    
    @Override
    public Order save(final Order order) {
        return orderRepository.save(order);
    }
    
    @Override
    public List<OrderInventory> saveOrderInventories(final List<OrderInventory> orderInventories) {
        return orderInventoryRepository.saveAll(orderInventories);
    }
    
    @Override
    public Order getOrderById(final Long orderIdentifier) {
        return orderRepository.findById(orderIdentifier)
            .orElseThrow(()->new ModulithException("Order with id:'" + orderIdentifier + "' not found"));
    }
    
    @Override
    public BigDecimal orderIdAmount(final Long orderId) {
        return orderInventoryRepository.orderIdAmount(orderId);
    }
    
}
