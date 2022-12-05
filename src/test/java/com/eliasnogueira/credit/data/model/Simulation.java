package com.eliasnogueira.credit.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Simulation {

    @JsonIgnore
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private BigDecimal amount;
    private Integer installments;
    private Boolean insurance;
}
