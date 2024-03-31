package com.eliasnogueira.credit.tests;

import com.eliasnogueira.credit.data.model.Simulation;
import com.eliasnogueira.credit.dto.SimulationDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AssertJExamplesTest {
    @Test
    @Disabled
    void recursiveComparisonTest() {
        var simulation1 = Simulation.builder().name("John").cpf("9582728395")
                .email("john@gmail.com").amount(new BigDecimal("500")).installments(1)
                .insurance(false).build();

        var simulation2 = SimulationDto.builder().name("John").cpf("9582728395")
                .email("john@gmail.com").amount(new BigDecimal("500")).installments(1)
                .insurance(false).build();

        assertThat(simulation1).usingRecursiveComparison().isEqualTo(simulation2);
    }
}
