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

package com.eliasnogueira.credit.tests;

import com.eliasnogueira.credit.BaseAPI;
import com.eliasnogueira.credit.data.factory.SimulationDataFactory;
import com.eliasnogueira.credit.data.model.Simulation;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SimulationsIntegrationTest extends BaseAPI {

    protected final SimulationDataFactory simulationDataFactory = new SimulationDataFactory();

    private static final String FAILED_VALIDATION =
        "com.eliasnogueira.credit.data.provider.SimulationDataProvider#failedValidations";

    /*
     * not that, in order to assert the amount without problem, we must enable a configuration
     * it's located at BaseAPI class
     */
    @Test
    @DisplayName("Should validate one existing simulation")
    void getOneExistingSimulation() {
        var existingSimulation = simulationDataFactory.oneExistingSimulation();

        given().
            pathParam("cpf", existingSimulation.getCpf()).
        when().
            get("/simulations/{cpf}").
        then().
            statusCode(SC_OK).
            body(
                "name", equalTo(existingSimulation.getName()),
                "cpf", equalTo(existingSimulation.getCpf()),
                "email", equalTo(existingSimulation.getEmail()),
                "amount", equalTo(existingSimulation.getAmount()),
                "installments", equalTo(existingSimulation.getInstallments()),
                "insurance", equalTo(existingSimulation.getInsurance())
            );
    }

    @Test
    @DisplayName("Should validate all existing simulations")
    void getAllExistingSimulations() {
        var existingSimulations = simulationDataFactory.allExistingSimulations();

        var simulationsRequested =
            when().
                get("simulations/").
            then().
                statusCode(SC_OK).
                extract().
                   as(Simulation[].class);

        Assertions.assertThat(existingSimulations).contains(simulationsRequested);
    }

    @Test
    @DisplayName("Should filter by name a non-existing simulation")
    void simulationByNameNotFound() {
        given().
            queryParam("name", simulationDataFactory.nonExistentName()).
        when().
            get("/simulations").
        then().
            statusCode(SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Should find a simulation filtered by name")
    void returnSimulationByName() {
        var existingSimulation = simulationDataFactory.oneExistingSimulation();

        given().
            queryParam("name", existingSimulation.getName()).
        when().
            get("/simulations").
        then().
            statusCode(SC_OK).
            body(
                "[0].name", equalTo(existingSimulation.getName()),
                "[0].cpf", equalTo(existingSimulation.getCpf()),
                "[0].email", equalTo(existingSimulation.getEmail()),
                "[0].amount", equalTo(existingSimulation.getAmount()),
                "[0].installments", equalTo(existingSimulation.getInstallments()),
                "[0].insurance", equalTo(existingSimulation.getInsurance())
            );
    }

    /*
     * here there is a header validation
     */
    @Test
    @DisplayName("Should create a new simulation")
    void createNewSimulationSuccessfully() {
        var simulation = simulationDataFactory.validSimulation();

        given().
            contentType(ContentType.JSON).
            body(simulation).
        when().
            post("/simulations").
        then().
            statusCode(SC_CREATED).
            header("Location",
                    equalTo("http://localhost:8088/api/v1/simulations/" + simulation.getCpf()));
    }

    
    @ParameterizedTest(name = "Scenario: {2}")
    @MethodSource(value = FAILED_VALIDATION)
    @DisplayName("Should validate all the invalid scenarios")
    void invalidSimulations(Simulation invalidSimulation, String path, String validationMessage) {
        given().
            contentType(ContentType.JSON).
            body(invalidSimulation).
        when().
            post("/simulations").
        then().
            statusCode(SC_UNPROCESSABLE_ENTITY).
            body(path, is(validationMessage));
    }

    @Test
    @DisplayName("Should validate an CFP duplication")
    void simulationWithDuplicatedCpf() {
        var existingSimulation = simulationDataFactory.oneExistingSimulation();
        given().
            contentType(ContentType.JSON).
            body(existingSimulation).
        when().
            post("/simulations/").
        then().
            statusCode(SC_CONFLICT).
            body("message", is("CPF already exists"));
    }

    @Test
    @DisplayName("Should delete an existing simulation")
    void deleteSimulationSuccessfully() {
        var existingSimulation = simulationDataFactory.oneExistingSimulation();

        given().
            pathParam("cpf", existingSimulation.getCpf()).
        when().
            delete("/simulations/{cpf}").
        then().
            statusCode(SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Should validate the return when a non-existent simulation is sent")
    void notFoundWhenDeleteSimulation() {
        given().
            pathParam("cpf", simulationDataFactory.notExistentCpf()).
        when().
            delete("/simulations/{cpf}").
        then().
            statusCode(SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Should update an existing simulation")
    void changeSimulationSuccessfully() {
        var existingSimulation = simulationDataFactory.oneExistingSimulation();

        var simulation = simulationDataFactory.validSimulation();
        simulation.setCpf(existingSimulation.getCpf());
        simulation.setInsurance(existingSimulation.getInsurance());

        var simulationReturned =
            given().
                contentType(ContentType.JSON).
                pathParam("cpf", existingSimulation.getCpf()).
                body(simulation).
            when().
                put("/simulations/{cpf}").
            then().
                statusCode(SC_OK).
                extract().
                    as(Simulation.class);

        assertThat("Simulation are not the same",
            simulationReturned, is(simulation));
    }

    @Test
    @DisplayName("Should validate the return of an update for a non-existent CPF")
    void changeSimulationCpfNotFound() {
        var simulation = simulationDataFactory.validSimulation();

        given().
            contentType(ContentType.JSON).
            pathParam("cpf", simulationDataFactory.notExistentCpf()).
            body(simulation).
        when().
            put("/simulations/{cpf}").
        then().
            statusCode(SC_NOT_FOUND);
    }
}
