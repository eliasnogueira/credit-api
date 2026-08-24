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
import com.eliasnogueira.credit.entity.Restriction;
import com.eliasnogueira.credit.entity.SimulationBuilder;
import com.eliasnogueira.credit.entity.Type;
import com.eliasnogueira.credit.repository.RestrictionRepository;
import com.eliasnogueira.credit.repository.SimulationRepository;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.config;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public abstract class BaseAPI {

    private String baseURI = "http://localhost";

    private String basePath = "/api/v1";

    @LocalServerPort
    protected int port;

    @Autowired
    private RestrictionRepository restrictionRepository;

    @Autowired
    private SimulationRepository simulationRepository;

    /*
     * This is done using @BeforeEach instead of @BeforeAll because Spring does not support the @Value for static fields
     */
    @BeforeEach
    void beforeEachTest() {
        simulationRepository.deleteAll();
        restrictionRepository.deleteAll();
        restrictionRepository.save(new Restriction("97093236014", Type.JUDICIAL_ISSUE.get()));
        restrictionRepository.save(new Restriction("60094146012", Type.CREDIT_CARD.get()));
        restrictionRepository.save(new Restriction("84809766080", Type.BANKING.get()));
        restrictionRepository.save(new Restriction("62648716050", Type.CREDIT_SCORE.get()));
        restrictionRepository.save(new Restriction("26276298085", Type.CREDIT_SCORE.get()));
        restrictionRepository.save(new Restriction("01317496094", Type.CREDIT_CARD.get()));
        restrictionRepository.save(new Restriction("55856777050", Type.BANKING.get()));
        restrictionRepository.save(new Restriction("19626829001", Type.JUDICIAL_ISSUE.get()));
        restrictionRepository.save(new Restriction("24094592008", Type.BANKING.get()));
        restrictionRepository.save(new Restriction("58063164083", Type.BANKING.get()));
        simulationRepository.save(new SimulationBuilder().cpf("66414919004").name("Tom")
                .email("tom@gmail.com").amount(new java.math.BigDecimal("11000"))
                .installments(3).insurance(true).build());
        simulationRepository.save(new SimulationBuilder().cpf("17822386034").name("John")
                .email("john@gmail.com").amount(new java.math.BigDecimal("20000"))
                .installments(5).insurance(false).build());
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
