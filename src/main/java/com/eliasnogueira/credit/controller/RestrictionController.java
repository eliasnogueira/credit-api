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

package com.eliasnogueira.credit.controller;

import com.eliasnogueira.credit.entity.Restriction;
import com.eliasnogueira.credit.exception.v2.RestrictionException;
import com.eliasnogueira.credit.service.RestrictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Optional;

@RestController
public class RestrictionController {

    private final RestrictionService restrictionService;
    private static final String CPF_HAS_RESTRICTIONS = "CPF {0} has a restriction";

    public RestrictionController(RestrictionService restrictionService) {
        this.restrictionService = restrictionService;
    }

    @GetMapping("/api/v1/restrictions/{cpf}")
    public ResponseEntity<Void> one(@PathVariable String cpf) {
        Optional<Restriction> restrictionOptional = restrictionService.findByCpf(cpf);

        if (restrictionOptional.isPresent()) {
            throw new com.eliasnogueira.credit.exception.v1.RestrictionException(
                    MessageFormat.format(CPF_HAS_RESTRICTIONS, cpf));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/v2/restrictions/{cpf}")
    public ResponseEntity<Void> oneV2(@PathVariable String cpf) {
        Optional<Restriction> restrictionOptional = restrictionService.findByCpf(cpf);

        if (restrictionOptional.isPresent()) {
            throw new RestrictionException(
                    MessageFormat.format(CPF_HAS_RESTRICTIONS, cpf), restrictionOptional.get().getType());
        }

        return ResponseEntity.notFound().build();
    }
}
