package com.eloarena.controller;

import com.eloarena.dto.CreatePlayerRequest;
import com.eloarena.model.Player;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eloarena.service.PlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = "http://localhost:3000")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public Player create(@Valid @RequestBody CreatePlayerRequest request) {
        return playerService.create(request.getName());
    }

    @GetMapping
    public List<Player> list() {
        return playerService.listAllOrderedByRating();
    }
}
