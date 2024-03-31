/*
 * MIT License
 *
 * Copyright (c) 2020 Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.eliasnogueira.credit;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.path.json.config.JsonPathConfig.NumberReturnType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.config;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("/application-test.properties")
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public abstract class BaseAPI {

    @Value("${credit-api.test.base-uri}")
    private String baseURI;

    @Value("${credit-api.test.base-path}")
    private String basePath;

    @Value("${credit-api.test.port}")
    private int port;

    /*
     * This is done using @BeforeEach instead of @BeforeAll because Spring does not support the @Value for static fields
     */
    @BeforeEach
    void beforeAllTests() {
        RestAssured.baseURI = baseURI;
        RestAssured.basePath = basePath;
        RestAssured.port = port;

        // solve the problem with big decimal assertions
        config = newConfig().
                jsonConfig(jsonConfig().numberReturnType(NumberReturnType.BIG_DECIMAL)).
                sslConfig(new SSLConfig().allowAllHostnames());

        RestAssured.useRelaxedHTTPSValidation();
    }
}
