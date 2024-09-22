package com.nl.paymentsmodulith.orders.infrastucture.web.model;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record OrderDto(@NotBlank(message = "customerName is required")
                       String customerName,
                       @NotBlank(message = "customerEmail is required")
                       String customerEmail,
                       @Valid
                       List<InventoryRequestDto> inventories) {
    
}
