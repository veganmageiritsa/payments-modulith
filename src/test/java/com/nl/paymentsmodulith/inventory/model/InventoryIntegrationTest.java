package com.nl.paymentsmodulith.inventory.model;

import org.springframework.modulith.test.ApplicationModuleTest;

import org.junit.jupiter.api.Test;

import com.nl.paymentsmodulith.ContainerIntegrationTest;
import static org.junit.jupiter.api.Assertions.*;

@ApplicationModuleTest
//    (mode = ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
class InventoryIntegrationTest extends ContainerIntegrationTest {
    
    @Test
    void verifyModule(){
        // Verify module dependencies
    }
}