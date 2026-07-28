package com.eloarena.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eloarena.dto.SimulationResult;
import com.eloarena.service.SimulationService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/simulate")
@CrossOrigin(origins = "http://localhost:3000")
public class SimulationController {
    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping
    public SimulationResult simulate(@RequestParam(defaultValue = "100") int battles) {
        return simulationService.simulate(battles);
    }

}
