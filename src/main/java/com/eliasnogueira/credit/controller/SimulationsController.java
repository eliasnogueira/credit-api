package com.eliasnogueira.credit.controller;

import com.eliasnogueira.credit.dto.SimulationDto;
import com.eliasnogueira.credit.dto.SimulationResponseDto;
import com.eliasnogueira.credit.service.SimulationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/simulations")
public class SimulationsController {
    private final SimulationService service;

    public SimulationsController(SimulationService service) {
        this.service = service;
    }

    @GetMapping({"", "/"})
    public List<SimulationResponseDto> getSimulation(@RequestParam(name = "name", required = false) String name) {
        return service.findAll(name);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<SimulationResponseDto> one(@PathVariable String cpf) {
        return ResponseEntity.ok(service.findByCpf(cpf));
    }

    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> newSimulation(@Valid @RequestBody SimulationDto simulation) {
        service.create(simulation);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cpf}").buildAndExpand(simulation.getCpf()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{cpf}")
    public SimulationResponseDto updateSimulation(@Valid @RequestBody SimulationDto simulation,
                                                  @PathVariable String cpf) {
        return service.update(cpf, simulation);
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String cpf) {
        service.delete(cpf);
    }
}
