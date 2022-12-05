package com.eliasnogueira.credit.tests;

import com.eliasnogueira.credit.BaseAPI;
import com.eliasnogueira.credit.data.changeless.RestrictionsData;
import com.eliasnogueira.credit.data.factory.RestrictionDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static java.text.MessageFormat.format;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RestrictionsIntegrationTest extends BaseAPI {

    private final RestrictionDataFactory restrictionDataFactory = new RestrictionDataFactory();

    @Test
    @DisplayName("Should query a CPF without restriction")
    void cpfWithNoRestriction() {
        given()
            .pathParam(RestrictionsData.CPF, restrictionDataFactory.cpfWithoutRestriction()).
        when()
            .get(RestrictionsData.GET_RESTRICTIONS).
        then()
            .statusCode(SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Should query a CPF with restriction")
    void cpfWithRestriction() {
        String cpfWithRestriction = restrictionDataFactory.cpfWithRestriction();

        given()
            .pathParam(RestrictionsData.CPF, cpfWithRestriction).
        when()
            .get(RestrictionsData.GET_RESTRICTIONS).
        then()
            .statusCode(SC_OK)
            .body("message", is(format("CPF {0} has a restriction", cpfWithRestriction)));
    }
}
