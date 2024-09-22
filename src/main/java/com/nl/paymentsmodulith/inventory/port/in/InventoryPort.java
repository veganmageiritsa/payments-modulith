package com.nl.paymentsmodulith.inventory.port.in;

import java.util.List;

import com.nl.paymentsmodulith.inventory.port.domain.InventoryDto;

public interface InventoryPort {
    InventoryDto fetchInventoryByName(String name);
    
    List<InventoryDto> fetchAllInName(List<String> inventoryNames);
}
