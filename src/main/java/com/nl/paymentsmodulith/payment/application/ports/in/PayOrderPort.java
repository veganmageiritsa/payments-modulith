package com.nl.paymentsmodulith.payment.application.ports.in;

import org.springframework.modulith.events.ApplicationModuleListener;

import com.nl.paymentsmodulith.orders.domain.events.OrderPaymentEvent;

public interface PayOrderPort {
    
    
    void on(OrderPaymentEvent event);
    
}
