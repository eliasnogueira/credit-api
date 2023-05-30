/*
 * MIT License
 *
 * Copyright (c) 2023 Elias Nogueira
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

package com.eliasnogueira.credit.integration.tests;

import com.eliasnogueira.credit.integration.BaseIntegrationApi;
import com.eliasnogueira.credit.repository.EventRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Callable;

import static io.restassured.RestAssured.given;
import static java.text.MessageFormat.format;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.http.HttpStatus.SC_OK;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class EventsTest extends BaseIntegrationApi {

    @Autowired
    private EventRepository eventRepository;

    @Test
    @Disabled("Test meant to run showing the async call, where the test will fail")
    @DisplayName("Check for an event with a restriction")
    void checkEventWithRestriction_Fail() {
        String cpf = "97093236014";

        checkForRestriction(cpf);

        given().pathParam("cpf", cpf).when().get("/events/{cpf}").then().statusCode(SC_OK);
    }

    @Test
    @DisplayName("Check for an event with a restriction")
    void checkEventWithRestriction_Success() {
        String cpf = "97093236014";

        checkForRestriction(cpf);

        await().atMost(10, SECONDS).until(eventIsFound(cpf));
        given().pathParam("cpf", cpf).when().get("/events/{cpf}").then().statusCode(SC_OK);
    }

    public Callable<Boolean> eventIsFound(String cpf) {
        return () -> eventRepository.findByCpf(cpf).isPresent();
    }

    private void checkForRestriction(String cpf) {
        given().pathParam("cpf", cpf)
                .when().get("/restrictions/{cpf}")
                .then().statusCode(SC_OK)
                .body("message", is(format("CPF {0} has a restriction", cpf)));
    }
}
