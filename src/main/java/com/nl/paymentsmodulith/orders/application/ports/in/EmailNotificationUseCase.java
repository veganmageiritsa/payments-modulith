package com.nl.paymentsmodulith.orders.application.ports.in;

import com.nl.paymentsmodulith.orders.domain.events.EmailEvent;

public interface EmailNotificationUseCase {
    
    void sendEmail(EmailEvent event);}
