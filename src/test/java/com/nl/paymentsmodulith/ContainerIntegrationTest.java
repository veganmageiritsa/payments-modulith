package com.nl.paymentsmodulith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest

//@ActiveProfiles(value = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class ContainerIntegrationTest {
    
    static final PostgreSQLContainer<?> postgreSQLContainer;
    
    static {
        postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:latest")
                .withReuse(true)
                .withDatabaseName("testcontainer")
                .withUsername("postgres")
                .withPassword("postgres");
        
        postgreSQLContainer.start();
    }
    
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        r.add("spring.datasource.username", postgreSQLContainer::getUsername);
        r.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }
    
}
