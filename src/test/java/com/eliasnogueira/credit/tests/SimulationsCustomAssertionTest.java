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

package com.eliasnogueira.credit.tests;

import com.eliasnogueira.credit.assertion.SimulationAssert;
import com.eliasnogueira.credit.assertion.SimulationSoftAssert;
import com.eliasnogueira.credit.data.model.Simulation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class SimulationsCustomAssertionTest {

    @Test
    @Disabled
    @DisplayName("Simulation using hard assertions")
    void simulationValidationErrorNormalAssertion() {
        var simulation = Simulation.builder().name("John").cpf("9582728395").email("john@gmail.com")
                .amount(new BigDecimal("500")).installments(1).insurance(false).build();

        SimulationAssert.assertThat(simulation).hasValidInstallments();
        SimulationAssert.assertThat(simulation).hasValidAmount(); // never gets here
    }

    @Test
    @Disabled
    @DisplayName("Simulation using soft assertions")
    void simulationValidationErrorSoftAssertion() {
        var simulation = Simulation.builder().name("John").cpf("9582728395").email("john@gmail.com")
                .amount(new BigDecimal("500")).installments(1).insurance(false).build();

        SimulationSoftAssert.assertSoftly(softly -> {
            softly.assertThat(simulation).hasValidInstallments();
            softly.assertThat(simulation).hasValidAmount();
        });
    }
}
