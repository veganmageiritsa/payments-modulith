package com.nl.paymentsmodulith.payment.application.ports;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import com.nl.paymentsmodulith.exception.ModulithException;
import com.nl.paymentsmodulith.orders.domain.events.CompleteOrderEvent;
import com.nl.paymentsmodulith.orders.domain.events.OrderPaymentEvent;
import com.nl.paymentsmodulith.payment.application.ports.in.PayOrderPort;
import com.nl.paymentsmodulith.payment.model.Payment;
import com.nl.paymentsmodulith.payment.repository.PaymentRepository;


@Service
public class PaymentEventListener implements PayOrderPort {
    
    private final PaymentRepository paymentRepository;
    
    public PaymentEventListener(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    @Override
    @ApplicationModuleListener
    public void on(OrderPaymentEvent event){
        
        Payment payment = new Payment();
        paymentRepository.save( payment.initiatePayment(event));
    }
    
    @ApplicationModuleListener
    public void on(CompleteOrderEvent event) {
        
        Payment payment = paymentRepository.getPaymentByOrderId(event.orderId())
                                           .orElseThrow(()-> new ModulithException("Payment not found for order: " + event.orderId()));
        paymentRepository.save(payment.completePayment());
    }
}
