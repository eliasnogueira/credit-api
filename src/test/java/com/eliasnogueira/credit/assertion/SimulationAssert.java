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

package com.eliasnogueira.credit.assertion;

import com.eliasnogueira.credit.data.model.Simulation;
import org.assertj.core.api.AbstractAssert;

import java.math.BigDecimal;

public class SimulationAssert extends AbstractAssert<SimulationAssert, Simulation> {

    protected SimulationAssert(Simulation actual) {
        super(actual, SimulationAssert.class);
    }

    public static SimulationAssert assertThat(Simulation actual) {
        return new SimulationAssert(actual);
    }

    public SimulationAssert hasValidInstallments() {
        isNotNull();

        if (actual.getInstallments() < 2 || actual.getInstallments() > 48) {
            failWithMessage("Installments must be must be equal or greater than 2 and equal or less than 48");
        }

        return this;
    }

    public SimulationAssert hasValidAmount() {
        isNotNull();

        var minimum = new BigDecimal("1.000");
        var maximum = new BigDecimal("40.000");

        if (actual.getAmount().compareTo(minimum) < 0 || actual.getAmount().compareTo(maximum) > 0) {
            failWithMessage("Amount must be equal or greater than $ 1.000 or equal or less than than $ 40.000");
        }

        return this;
    }
}
