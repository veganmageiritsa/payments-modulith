package com.nl.paymentsmodulith.orders.application.ports.in;


import com.nl.paymentsmodulith.orders.infrastucture.web.model.OrderDto;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.OrderResponseDto;

public interface CreateOrderUseCase {
    OrderResponseDto createOrder(OrderDto orderDto);
}
