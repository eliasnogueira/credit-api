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

package com.eliasnogueira.credit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value = "Simulation")
public class SimulationDto {

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Long id;

    @ApiModelProperty(required = true, example = "97093236014",
        notes = "CPF cannot be empty")
    private String cpf;

    @ApiModelProperty(position = 1, required = true, example = "John Doe",
        notes = "Name cannot be empty")
    private String name;

    @ApiModelProperty(position = 2, required = true, example = "email@email.com",
        notes = "E-mail must be valid")
    private String email;

    @ApiModelProperty(position = 3, required = true, example = "1200",
        notes = "Amount must be equal or greater than $ 1.000 or less than $ 40.000")
    private BigDecimal amount;

    @ApiModelProperty(position = 4, required = true, example = "3",
        notes = "Installments must be equal or greater than 2 or less than 48")
    private Integer installments;

    @ApiModelProperty(position = 5, required = true, example = "true")
    private Boolean insurance;
}
