package com.eliasnogueira.credit.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
