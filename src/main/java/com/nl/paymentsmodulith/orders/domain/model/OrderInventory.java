package com.nl.paymentsmodulith.orders.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "order_inventory", indexes = {
    @Index(name = "ord_idx", columnList = "order_id"),
    @Index(name = "inv_idx", columnList = "inventory_id")
})
public class OrderInventory {
    
    @EmbeddedId
    private OrderInventoryId id;
    private int quantity;
    private BigDecimal totalPrice;
}
