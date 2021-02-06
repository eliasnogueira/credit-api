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
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimulationDto {

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(name = "CPF", position = 2, example = "9709323014")
    private String cpf;

    @ApiModelProperty(name = "Name", position = 1, example = "John Doe")
    private String name;

    @ApiModelProperty(name = "Email", position = 3, example = "john.doe@gmail.com")
    private String email;

    @ApiModelProperty(name = "Amount", position = 4, example = "1200")
    private BigDecimal amount;

    @ApiModelProperty(name = "Installments", position = 5, example = "3")
    private Integer installments;

    @ApiModelProperty(name = "Insurance", position = 5, example = "true")
    private Boolean insurance;
}
