package com.nl.paymentsmodulith.inventory.port.domain;


import java.math.BigDecimal;

import com.nl.paymentsmodulith.inventory.model.Inventory;

public record InventoryDto(Long id, String name, String description, BigDecimal price){
    public static InventoryDto from(Inventory inventory) {
        return new InventoryDto(inventory.getId(), inventory.getName(), inventory.getDescription(), inventory.getPrice());
    }
}
