package com.nl.paymentsmodulith.orders.domain.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.nl.paymentsmodulith.inventory.port.in.InventoryPort;
import com.nl.paymentsmodulith.orders.domain.events.CompleteOrderEvent;
import com.nl.paymentsmodulith.orders.domain.events.EmailEvent;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.OrderDto;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "orders", indexes = {
    @Index(name = "idx_id", columnList = "id"),
    @Index(name = "idx_identifier", columnList = "order_identifier")
})
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate

public class Order extends AbstractAggregateRoot<Order> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_gen")
    @SequenceGenerator(name = "orders_gen", sequenceName = "orders_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    
    private String orderIdentifier;
    
    private String customerName;
    
    private String customerEmail;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    
    public Order initializeOrder(OrderDto orderDto) {
        this.customerEmail = orderDto.customerEmail();
        this.customerName = orderDto.customerName();
        this.orderStatus = OrderStatus.CREATED;
        this.orderIdentifier = UUID.randomUUID().toString();
        return this;
    }
    
    @Getter
    @AllArgsConstructor
    enum OrderStatus {
        CREATED("Created"),
        IN_PROGRESS("In progress"),
        COMPLETED("Completed"),
        CANCELLED("Cancelled");
        
        private final String status;
    }
    
    @Converter
    static class StatusConverter implements AttributeConverter<OrderStatus, String> {
        
        @Override
        public String convertToDatabaseColumn(final OrderStatus orderStatus) {
            return Arrays.stream(OrderStatus.values())
                         .filter(v -> v.equals(orderStatus))
                         .map(OrderStatus::getStatus)
                         .findFirst().orElseThrow(() -> new IllegalArgumentException("No Status Found"));
        }
        
        @Override
        public OrderStatus convertToEntityAttribute(final String s) {
            return Arrays.stream(OrderStatus.values())
                         .filter(v -> v.getStatus()
                                       .equals(s)).
                         findFirst().orElseThrow(() -> new IllegalArgumentException("No Status Found"));
        }
        
    }
    
    public Order pay(final CompleteOrderEvent completeOrderEvent, final BigDecimal amount) {
        this.orderStatus = OrderStatus.COMPLETED;
        
        registerEvent(completeOrderEvent);
        registerEvent(new EmailEvent(this.getCustomerEmail(),
                                     this.getCustomerName(),
                                     this.id,
                                     amount,
                                     true));
        return this;
    }
    
}
