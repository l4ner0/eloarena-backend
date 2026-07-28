package com.eloarena.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eloarena.dto.QueueStatusResponse;
import com.eloarena.model.Battle;
import com.eloarena.model.BattleStatus;
import com.eloarena.repository.BattleRepository;
import com.eloarena.service.MatchmakingService;

@RestController
@RequestMapping("/api/queue")
@CrossOrigin(origins = "http://localhost:3000")
public class QueueController {
    private final MatchmakingService matchmakingService;
    private final BattleRepository battleRepository;

    public QueueController(MatchmakingService matchmakingService, BattleRepository battleRepository) {
        this.matchmakingService = matchmakingService;
        this.battleRepository = battleRepository;
    }

    @PostMapping("/{playerId}")
    public void enqueue(@PathVariable Long playerId) {
        matchmakingService.enqueue(playerId);
    }

    @DeleteMapping("/{playerId}")
    public void dequeue(@PathVariable Long playerId) {
        matchmakingService.dequeue(playerId);
    }

    @GetMapping("/{playerId}/status")
    public QueueStatusResponse status(@PathVariable Long playerId) {
        if (matchmakingService.isQueued(playerId)) {
            return QueueStatusResponse.waiting();
        }

        Optional<Battle> battle = battleRepository.findPendingBattleForPlayer(BattleStatus.PENDING, playerId);
        return battle.map(b -> QueueStatusResponse.matched(b.getId())).orElse(QueueStatusResponse.notFound());
    }

}
