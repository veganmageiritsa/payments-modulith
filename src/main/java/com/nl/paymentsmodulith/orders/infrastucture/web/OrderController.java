package com.nl.paymentsmodulith.orders.infrastucture.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nl.paymentsmodulith.orders.application.ports.in.CreateOrderUseCase;
import com.nl.paymentsmodulith.orders.application.ports.in.OrderPaymentUseCase;
import com.nl.paymentsmodulith.orders.domain.events.CompleteOrderEvent;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.CompleteOrderResponse;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.OrderDto;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.OrderResponseDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

private final CreateOrderUseCase  createOrderUseCase;

private final OrderPaymentUseCase completeOrderUseCase;
    
    public OrderController(final CreateOrderUseCase createOrderUseCase,
                           final OrderPaymentUseCase completeOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.completeOrderUseCase = completeOrderUseCase;
    }
    
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody @Valid OrderDto orderDto) {
        return new ResponseEntity<>(createOrderUseCase.createOrder(orderDto), HttpStatus.OK);
    }
    
    
    @PostMapping(path = "complete")
    public ResponseEntity<CompleteOrderResponse> completeOrder(@RequestBody @Valid CompleteOrderEvent completeOrderEvent) {
        return new ResponseEntity<>(completeOrderUseCase.completeOrder(completeOrderEvent), HttpStatus.OK);
    }
}
