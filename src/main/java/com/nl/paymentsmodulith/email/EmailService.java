package com.nl.paymentsmodulith.email;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import com.nl.paymentsmodulith.orders.domain.events.EmailEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {
    
    
    @ApplicationModuleListener
    public void on(EmailEvent event){
      
      log.info("Sending email to " + event.email());
      log.info("Customer name is " + event.customerName());
      log.info("Order identifier is " + event.orderIdentifier());
      log.info("Total amount is " + event.totalAmount());
      log.info("Order complete is " + event.orderComplete());
    }
}
