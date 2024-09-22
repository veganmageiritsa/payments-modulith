package com.nl.paymentsmodulith.orders.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import org.junit.jupiter.api.Test;

import com.nl.paymentsmodulith.ContainerIntegrationTest;
import com.nl.paymentsmodulith.orders.application.OrderManagement;
import com.nl.paymentsmodulith.orders.domain.events.OrderPaymentEvent;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.InventoryRequestDto;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.OrderDto;
import com.nl.paymentsmodulith.orders.infrastucture.web.model.OrderResponseDto;
import static org.junit.jupiter.api.Assertions.*;

@ApplicationModuleTest(mode = ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
class OrderIntegrationTest extends ContainerIntegrationTest {
    
    @Autowired
    OrderManagement orderManagement;
    
    @Test
    void verifyModule() {
    
    }
    
    @Test
    void createOrder() {
        var inventoryRequestDtos = List.of(new InventoryRequestDto("pencil", 3),
                                           new InventoryRequestDto("book", 2));
        
        var orderResponse = orderManagement.createOrder(new OrderDto("email", "name", inventoryRequestDtos));
        
        assertNotNull(orderResponse);
    }
    
    @Test
    void publishOrderPayment(Scenario scenario){
        scenario.publish(new OrderPaymentEvent(1L, BigDecimal.TEN))
            .andWaitForEventOfType(OrderPaymentEvent.class)
            .matching(event -> event.amount().equals(BigDecimal.TEN))
            .toArriveAndVerify(event -> assertEquals(BigDecimal.TEN, event.amount()));
    }
    
}