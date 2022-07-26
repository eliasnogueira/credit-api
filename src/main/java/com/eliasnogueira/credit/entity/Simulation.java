/*
 * MIT License
 *
 * Copyright (c) today.year Elias Nogueira
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

package com.eliasnogueira.credit.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "cpf_unique", columnNames = "cpf")
})
public class Simulation {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "CPF cannot be empty")
    @NotBlank(message = "CPF cannot be empty")
    private String cpf;

    @NotNull(message = "E-mail cannot be empty")
    @Email
    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "E-mail must be valid")
    private String email;

    @NotNull(message = "Amount cannot be empty")
    @Min(value = 1000, message = "Amount must be equal or greater than $ 1.000")
    @Max(value = 40000, message = "Amount must be equal or less than than $ 40.000")
    private BigDecimal amount;

    @NotNull(message = "Installments cannot be empty")
    @Min(value = 2, message = "Installments must be equal or greater than 2")
    @Max(value = 48, message = "Installments must be equal or less than 48")
    private Integer installments;

    @NotNull(message = "One of the insurance options must be selected")
    private Boolean insurance;
}
