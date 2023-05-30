package com.eliasnogueira.credit.integration;

import io.restassured.config.SSLConfig;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class BaseIntegrationApi {
    @BeforeAll
    public static void beforeAllTests() {
        baseURI = "http://localhost";
        basePath = "/api/v1";
        port = 8088;

        // solve the problem with big decimal assertions
        config = newConfig().
                jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)).
                sslConfig(new SSLConfig().allowAllHostnames());

        useRelaxedHTTPSValidation();
        enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
