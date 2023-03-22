package com.eliasnogueira.credit.unit;

import com.eliasnogueira.credit.dto.SimulationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SimulationTest {

    @Autowired
    private MockMvc mock;

    @Test
    void shouldNotAddSimulationWithInstallmentsLessThan() throws Exception {
        var simulation = SimulationDto.builder().cpf("97093236014").email("john.doe@gmail.com").name("John Doe")
                .installments(1).amount(new BigDecimal("20000")).insurance(true).build();
        mock.perform(post("/api/v1/simulations/").content(new ObjectMapper().writeValueAsString(simulation)).contentType(APPLICATION_JSON)).andDo(print()).andExpect(status().isUnprocessableEntity());
    }
}
