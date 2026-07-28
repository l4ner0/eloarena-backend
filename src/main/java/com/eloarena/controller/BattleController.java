package com.eloarena.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eloarena.dto.ReportResultRequest;
import com.eloarena.model.Battle;
import com.eloarena.service.BattleService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/battles")
@CrossOrigin(origins = "http://localhost:3000")
public class BattleController {

    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @GetMapping("/{id}")
    public Battle get(@PathVariable Long id) {
        return this.battleService.findById(id);
    }

    @PostMapping("/{id}/result")
    public Battle reportResult(@PathVariable Long id, @Valid @RequestBody ReportResultRequest request) {
        return this.battleService.reportResult(id, request.getWinnerId());
    }

}
