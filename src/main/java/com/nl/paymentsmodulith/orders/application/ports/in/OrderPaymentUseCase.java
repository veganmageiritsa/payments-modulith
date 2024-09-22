package com.nl.paymentsmodulith.orders.application.ports.in;

import com.nl.paymentsmodulith.orders.domain.events.CompleteOrderEvent;
import com.nl.paymentsmodulith.orders.domain.events.OrderPaymentEvent;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.CompleteOrderResponse;

public interface OrderPaymentUseCase {
    
    CompleteOrderResponse completeOrder(CompleteOrderEvent completeOrderEvent);
    
}
