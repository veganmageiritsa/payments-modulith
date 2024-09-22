package com.nl.paymentsmodulith.eventpublication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import org.junit.jupiter.api.Test;

import com.nl.paymentsmodulith.ContainerIntegrationTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ApplicationModuleTest
class EventPublicationIntegrationTest extends ContainerIntegrationTest {
    
    @Autowired
    EventPublicationService service;
    
    
    @Test
    void getUncompletedEventPublicationTest(){
        
        List<CustomEventPublication> uncompletedEvents = service.getUncompletedEvents();
        
        uncompletedEvents
            .forEach(ev -> {
                assertThat(ev).isInstanceOf(CustomEventPublication.class);
                assertThat(ev.getCompletionDate()).isNull();
            });
    }
}