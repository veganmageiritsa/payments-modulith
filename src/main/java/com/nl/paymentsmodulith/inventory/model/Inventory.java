package com.nl.paymentsmodulith.inventory.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "inventory", indexes = @Index(name = "idx_name", columnList = "name"))
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_gen")
    @SequenceGenerator(name = "inventory_gen", sequenceName = "inventory_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    
    private String name;
    
    private String description;
    
    private BigDecimal price;
    
    
    
}
