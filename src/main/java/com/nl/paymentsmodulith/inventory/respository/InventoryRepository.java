package com.nl.paymentsmodulith.inventory.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nl.paymentsmodulith.inventory.model.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    
    Optional<Inventory> getInventoryByName(String name);
    
    List<Inventory> getInventoriesByNameIn(List<String> names);
    
    
}
