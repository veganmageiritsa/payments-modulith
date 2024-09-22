package com.nl.paymentsmodulith.orders.domain.model;

import java.io.Serial;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderInventoryId implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 2916653291384990853L;
    
    private long orderId;
    
    private long inventoryId;
    
    
    public OrderInventoryId(final long orderId, final Long inventoryId) {
        this.orderId = orderId;
        this.inventoryId = inventoryId;
    }
    
    public OrderInventoryId() {
    }
    
}
