package com.eliasnogueira.credit.config;

import com.eliasnogueira.credit.data.database.DatabaseContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.JdbcDatabaseContainer;

@TestConfiguration
class TestcontainersConfiguration {

    private static final Logger log = LogManager.getLogger(TestcontainersConfiguration.class);

    @Value("${database.integration.test}")
    private static String databaseIntegrationTest;

    static {
        log.info("Starting database container using {}", databaseIntegrationTest);
        DatabaseContainer.valueOf(databaseIntegrationTest.toUpperCase()).container().start();
    }

//    @Bean
//    JdbcDatabaseContainer<?> databaseContainer() {
//        log.info("Starting database container using {}", databaseIntegrationTest);
//        return DatabaseContainer.valueOf(databaseIntegrationTest.toUpperCase()).container();
//    }

    @DynamicPropertySource
    public void registerDynamicProperties(DynamicPropertyRegistry registry, JdbcDatabaseContainer<?> container) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.jpa.properties.hibernate.dialect",
                () -> DatabaseContainer.valueOf(databaseIntegrationTest.toUpperCase()).hibernateDialect());
    }
}
