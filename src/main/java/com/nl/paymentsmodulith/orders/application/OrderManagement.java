package com.nl.paymentsmodulith.orders.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nl.paymentsmodulith.exception.ModulithException;
import com.nl.paymentsmodulith.inventory.port.domain.InventoryDto;
import com.nl.paymentsmodulith.inventory.port.in.InventoryPort;
import com.nl.paymentsmodulith.orders.application.ports.in.CreateOrderUseCase;
import com.nl.paymentsmodulith.orders.application.ports.in.OrderPaymentUseCase;
import com.nl.paymentsmodulith.orders.application.ports.out.OrderDatabase;
import com.nl.paymentsmodulith.orders.domain.events.CompleteOrderEvent;
import com.nl.paymentsmodulith.orders.domain.events.EmailEvent;
import com.nl.paymentsmodulith.orders.domain.events.OrderPaymentEvent;
import com.nl.paymentsmodulith.orders.domain.model.Order;
import com.nl.paymentsmodulith.orders.domain.model.OrderInventory;
import com.nl.paymentsmodulith.orders.domain.model.OrderInventoryId;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.CompleteOrderResponse;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.InventoryRequestDto;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.OrderDto;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.OrderResponseDto;

@Service
@Transactional
public class OrderManagement implements CreateOrderUseCase, OrderPaymentUseCase {
    
    private final InventoryPort inventoryPort;
    
    private final OrderDatabase orderDatabase;
    
    private final OrderPublisherService orderPublisherService;
    
    public OrderManagement(final InventoryPort inventoryPort,
                           final OrderDatabase orderDatabase,
                           final OrderPublisherService orderPublisherService) {
        this.inventoryPort = inventoryPort;
        this.orderDatabase = orderDatabase;
        this.orderPublisherService = orderPublisherService;
    }
    
    @Override
    public OrderResponseDto createOrder(final OrderDto orderDto) {
        var inventoryNames = inventoryPort.fetchAllInName(orderDto.inventories()
                                                                  .stream()
                                                                  .map(InventoryRequestDto::inventoryName)
                                                                  .toList());
        
        var order = new Order().initializeOrder(orderDto);
        order = orderDatabase.save(order);
        var inventories = addInventoriesToOrder(orderDto, inventoryNames, order.getId());
        var totalAmount = inventories.stream()
                                     .map(OrderInventory::getTotalPrice)
                                     .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        orderPublisherService.completeOrder(new OrderPaymentEvent(order.getId(), totalAmount),
                                            new EmailEvent(orderDto.customerEmail(), orderDto.customerName(), order.getId(), totalAmount, false));
        return new OrderResponseDto("Order created successfully", HttpStatus.OK.value());
    }
    
    @Override
    public CompleteOrderResponse completeOrder(CompleteOrderEvent completeOrderEvent) {
        Order order = orderDatabase.getOrderById(completeOrderEvent.orderId());
        var amount = orderDatabase.orderIdAmount(order.getId());
        order = order.pay(completeOrderEvent, amount);
        
        orderDatabase.save(order);
        
//        orderPublisherService.completePayment(completeOrderEvent, emailDto);
        return new CompleteOrderResponse(true);
        
    }
    
    
    private List<OrderInventory> addInventoriesToOrder(OrderDto orderDto, List<InventoryDto> inventories, long orderId) {
        List<OrderInventory> orderInventories = inventories
            .stream()
            .map(inventoryDto -> {
                OrderInventory orderInventory = new OrderInventory();
                return getInventoryRequestDtoByName(inventoryDto.name(), orderDto.inventories())
                    .map(inventoryRequestDto -> {
                        orderInventory.setId(new OrderInventoryId(orderId, inventoryDto.id()));
                        orderInventory.setQuantity(inventoryRequestDto.quantity());
                        
                        var totalPrice = inventoryDto.price().multiply(BigDecimal.valueOf(inventoryRequestDto.quantity()));
                        orderInventory.setTotalPrice(totalPrice);
                        return orderInventory;
                    }).orElse(null);
            }).filter(Objects::nonNull)
            .toList();
        
        return orderDatabase.saveOrderInventories(orderInventories);
        
    }
    
    private static Optional<InventoryRequestDto> getInventoryRequestDtoByName(String inventoryName, List<InventoryRequestDto> inventoryRequestDtoList) {
        
        return inventoryRequestDtoList
            .stream()
            .filter(inv -> inv.inventoryName().equals(inventoryName))
            .findFirst();
    }
    
    

    
}
