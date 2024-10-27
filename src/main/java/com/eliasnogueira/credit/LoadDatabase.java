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

import com.eliasnogueira.credit.entity.Restriction;
import com.eliasnogueira.credit.entity.SimulationBuilder;
import com.eliasnogueira.credit.entity.Type;
import com.eliasnogueira.credit.repository.RestrictionRepository;
import com.eliasnogueira.credit.repository.SimulationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Map;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initCreditDatabase(RestrictionRepository restrictionRepository) {
        return args -> dataToInsert()
                .forEach((cpf, restriction) -> restrictionRepository.save(new Restriction(cpf, restriction)));
    }

    private Map<String, String> dataToInsert() {
        return Map.of(
                "97093236014", Type.JUDICIAL_ISSUE.get(),
                "60094146012", Type.CREDIT_CARD.get(),
                "84809766080", Type.BANKING.get(),
                "62648716050", Type.CREDIT_SCORE.get(),
                "26276298085", Type.CREDIT_SCORE.get(),
                "01317496094", Type.CREDIT_CARD.get(),
                "55856777050", Type.BANKING.get(),
                "19626829001", Type.JUDICIAL_ISSUE.get(),
                "24094592008", Type.BANKING.get(),
                "58063164083", Type.BANKING.get());
    }

    @Bean
    CommandLineRunner initRestrictionDatabase(SimulationRepository simulationRepository) {
        return args -> {
            simulationRepository.save(new SimulationBuilder().cpf("66414919004").name("Tom").email("tom@gmail.com")
                    .amount(new BigDecimal(11000)).installments(3).insurance(true).build());
            simulationRepository.save(new SimulationBuilder().cpf("17822386034").name("John").email("john@gmail.com")
                    .amount(new BigDecimal(20000)).installments(5).insurance(false).build());
        };
    }
}
