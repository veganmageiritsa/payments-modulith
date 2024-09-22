package com.nl.paymentsmodulith.orders.domain.events;



import com.nl.paymentsmodulith.eventaction.action.Action;
import com.nl.paymentsmodulith.eventaction.action.CustomEventMarker;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.jmolecules.event.types.DomainEvent;



@CustomEventMarker(eventAction = Action.COMPLETE_PAYMENT)
public record CompleteOrderEvent(
        @NotNull(message = "orderIdentifier is required")
        Long orderId,
        boolean paymentComplete) implements DomainEvent { }
