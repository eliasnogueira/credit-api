package com.eliasnogueira.credit.data.database;

import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;

public enum DatabaseContainer {

    MYSQL {
        @Override
        public JdbcDatabaseContainer<?> container() {
            return new MySQLContainer<>("mysql:9.1.0")
                    .withDatabaseName("credit")
                    .withUsername("test")
                    .withPassword("test");
        }

        @Override
        public String hibernateDialect() {
            return "org.hibernate.dialect.MySQLDialect";
        }

    }, POSTGRES {
        @Override
        @DynamicPropertySource
        public JdbcDatabaseContainer<?> container() {
            return new PostgreSQLContainer<>("postgres:15.3")
                    .withDatabaseName("credit")
                    .withUsername("test")
                    .withPassword("test");
        }

        @Override
        public String hibernateDialect() {
            return "org.hibernate.dialect.PostgreSQLDialect";
        }
    };

    public abstract JdbcDatabaseContainer<?> container();
    public abstract String hibernateDialect();
}
