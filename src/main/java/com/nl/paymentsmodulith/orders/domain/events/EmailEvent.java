package com.nl.paymentsmodulith.orders.domain.events;


import java.math.BigDecimal;

import com.nl.paymentsmodulith.eventaction.action.Action;
import com.nl.paymentsmodulith.eventaction.action.CustomEventMarker;
import org.jmolecules.event.types.DomainEvent;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 31 May, 2024
 */

@CustomEventMarker(eventAction = Action.EMAIL)
public record EmailEvent(String email,
                         String customerName,
                         Long orderIdentifier,
                         BigDecimal totalAmount,
                         boolean orderComplete) implements DomainEvent { }
