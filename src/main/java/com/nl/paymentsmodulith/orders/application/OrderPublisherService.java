package com.nl.paymentsmodulith.orders.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nl.paymentsmodulith.orders.domain.events.CompleteOrderEvent;
import com.nl.paymentsmodulith.orders.domain.events.EmailEvent;
import com.nl.paymentsmodulith.orders.domain.events.OrderPaymentEvent;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.CompleteOrderResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderPublisherService {
    
    private final ApplicationEventPublisher eventPublisher;
    
    public OrderPublisherService(final ApplicationEventPublisher publisher) {
        this.eventPublisher = publisher;
    }
    
    @Transactional
    public void completeOrder(final OrderPaymentEvent orderPaymentEvent, EmailEvent emailEvent) {
        
        log.info("Completing order payment with details{}", orderPaymentEvent);
        eventPublisher.publishEvent(orderPaymentEvent);
        
        log.info("Sending email for order {}", emailEvent);
        eventPublisher.publishEvent(emailEvent);
    }
    
    @Transactional
    public void completePayment(CompleteOrderEvent completeOrderEvent, EmailEvent emailEvent) {
        log.info("Attempting to complete payment {}", completeOrderEvent);
        eventPublisher.publishEvent(completeOrderEvent);
        
        log.info("Completed payment Email {}", emailEvent);
        eventPublisher.publishEvent(emailEvent);
    }
    
}
