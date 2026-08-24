/*
 * MIT License
 *
 * Copyright (c) 2026 Elias Nogueira
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
package com.eliasnogueira.credit.service.impl;

import com.eliasnogueira.credit.dto.SimulationDto;
import com.eliasnogueira.credit.dto.SimulationResponseDto;
import com.eliasnogueira.credit.entity.Simulation;
import com.eliasnogueira.credit.exception.SimulationException;
import com.eliasnogueira.credit.exception.RestrictionFoundException;
import com.eliasnogueira.credit.repository.SimulationRepository;
import com.eliasnogueira.credit.service.RestrictionService;
import com.eliasnogueira.credit.service.SimulationService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SimulationServiceImpl implements SimulationService {
    private static final String CPF_NOT_FOUND = "CPF %s not found";

    private final SimulationRepository repository;
    private final RestrictionService restrictionService;
    private final ModelMapper modelMapper;

    public SimulationServiceImpl(SimulationRepository repository, RestrictionService restrictionService,
                                 ModelMapper modelMapper) {
        this.repository = repository;
        this.restrictionService = restrictionService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SimulationResponseDto> findAll(String name) {
        var probe = new Simulation();
        probe.setName(name);
        var matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        var simulations = repository.findAll(Example.of(probe, matcher));
        if (simulations.isEmpty()) throw new SimulationException("Simulation not found!");
        return simulations.stream().map(this::toResponse).toList();
    }

    @Override
    public SimulationResponseDto findByCpf(String cpf) {
        return repository.findByCpf(cpf).map(this::toResponse)
                .orElseThrow(() -> new SimulationException(CPF_NOT_FOUND.formatted(cpf)));
    }

    @Override
    @Transactional
    public void create(SimulationDto dto) {
        if (restrictionService.findByCpf(dto.getCpf()).isPresent()) {
            throw new RestrictionFoundException("CPF " + dto.getCpf() + " has a restriction");
        }
        repository.save(modelMapper.map(dto, Simulation.class));
    }

    @Override
    @Transactional
    public SimulationResponseDto update(String cpf, SimulationDto dto) {
        var simulation = repository.findByCpf(cpf)
                .orElseThrow(() -> new SimulationException(CPF_NOT_FOUND.formatted(cpf)));
        if (dto.getName() != null) simulation.setName(dto.getName());
        if (dto.getCpf() != null) simulation.setCpf(dto.getCpf());
        if (dto.getEmail() != null) simulation.setEmail(dto.getEmail());
        if (dto.getInstallments() != null) simulation.setInstallments(dto.getInstallments());
        if (dto.getAmount() != null) simulation.setAmount(dto.getAmount());
        if (dto.getInsurance() != null) simulation.setInsurance(dto.getInsurance());
        return toResponse(repository.save(simulation));
    }

    @Override
    @Transactional
    public void delete(String cpf) {
        if (!repository.existsByCpf(cpf)) {
            throw new SimulationException(CPF_NOT_FOUND.formatted(cpf));
        }
        repository.deleteByCpf(cpf);
    }

    private SimulationResponseDto toResponse(Simulation simulation) {
        return new SimulationResponseDto(simulation.getName(), simulation.getCpf(), simulation.getEmail(),
                simulation.getAmount(), simulation.getInstallments(), simulation.getInsurance());
    }
}
