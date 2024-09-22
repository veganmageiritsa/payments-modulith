package com.nl.paymentsmodulith.orders.domain.events;

import java.math.BigDecimal;

import com.nl.paymentsmodulith.eventaction.action.Action;
import com.nl.paymentsmodulith.eventaction.action.CustomEventMarker;
import org.jmolecules.event.types.DomainEvent;

@CustomEventMarker(eventAction = Action.PAYMENT)
public record OrderPaymentEvent(Long orderId, BigDecimal amount)
    implements DomainEvent {
    
}
