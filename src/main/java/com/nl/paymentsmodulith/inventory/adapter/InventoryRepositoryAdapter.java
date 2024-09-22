package com.nl.paymentsmodulith.inventory.adapter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nl.paymentsmodulith.inventory.model.Inventory;
import com.nl.paymentsmodulith.inventory.port.domain.InventoryDto;
import com.nl.paymentsmodulith.inventory.port.in.InventoryPort;
import com.nl.paymentsmodulith.inventory.respository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryRepositoryAdapter implements InventoryPort {
    
    private final InventoryRepository inventoryRepository;
    
    @Override
    public InventoryDto fetchInventoryByName(final String name) {
        return inventoryRepository.getInventoryByName(name)
                                  .map(InventoryDto::from)
                                  .orElseThrow(()->new EntityNotFoundException(name));
    }
    
    @Override
    public List<InventoryDto> fetchAllInName(final List<String> inventoryNames) {
        return inventoryRepository.getInventoriesByNameIn(inventoryNames)
                                  .stream()
                                  .map(InventoryDto::from)
                                  .toList();
    }
    
}
