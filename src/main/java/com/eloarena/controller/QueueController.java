package com.eloarena.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.eloarena.dto.QueueStatusResponse;
import com.eloarena.model.Battle;
import com.eloarena.model.BattleStatus;
import com.eloarena.repository.BattleRepository;
import com.eloarena.service.MatchmakingService;

public class QueueController {
    private final MatchmakingService matchmakingService;
    private final BattleRepository battleRepository;

    public QueueController(MatchmakingService matchmakingService, BattleRepository battleRepository) {
        this.matchmakingService = matchmakingService;
        this.battleRepository = battleRepository;
    }

    public void enqueue(@PathVariable Long playerId) {
        matchmakingService.enqueue(playerId);
    }

    public void dequeue(@PathVariable Long playerId) {
        matchmakingService.dequeue(playerId);
    }

    public QueueStatusResponse status(@PathVariable Long playerId) {
        if (matchmakingService.isQueued(playerId)) {
            return QueueStatusResponse.waiting();
        }

        Optional<Battle> battle = battleRepository.findPendingBattleForPlayer(BattleStatus.PENDING, playerId);
        return battle.map(b -> QueueStatusResponse.matched(b.getId())).orElse(QueueStatusResponse.notFound());
    }

}
