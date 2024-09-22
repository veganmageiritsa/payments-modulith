package com.nl.paymentsmodulith.payment.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

import com.nl.paymentsmodulith.exception.ModulithException;
import com.nl.paymentsmodulith.orders.domain.events.OrderPaymentEvent;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "payments")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payments_gen")
    @SequenceGenerator(name = "payments_gen", sequenceName = "payments_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long orderId;
    private BigDecimal amount;
    private Timestamp paymentDate = Timestamp.from(Instant.now());
    private PaymentStatus status = PaymentStatus.INCOMPLETE;
    
    @Getter
    @AllArgsConstructor
    public enum PaymentStatus {
        INCOMPLETE("InComplete"), COMPLETED("Completed"), FAILED("Failed");
        
        private final String code;
    }
    
    @Converter(autoApply = true)
    public static class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {
        @Override
        public String convertToDatabaseColumn(PaymentStatus status) {
            if(status == null) throw new ModulithException("Status can not be null");
            return status.getCode();
        }
        
        @Override
        public PaymentStatus convertToEntityAttribute(String code) {
            if(code == null) throw new IllegalArgumentException("Code can not be null");
            return Arrays.stream(PaymentStatus.values())
                         .filter(s -> s.getCode().equalsIgnoreCase(code))
                         .findFirst().orElseThrow(ModulithException::new);
        }
    }
    
    public Payment initiatePayment(final OrderPaymentEvent event) {
        this.orderId = event.orderId();
        this.amount = event.amount();
        return this;
    }
    
    public Payment completePayment() {
        this.status = PaymentStatus.COMPLETED;
        return this;
    }
    
}
