package com.eloarena.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eloarena.exception.BattleNotFoundException;
import com.eloarena.exception.InvalidBattleResultException;
import com.eloarena.exception.PlayerNotFoundException;
import com.eloarena.model.Battle;
import com.eloarena.model.BattleStatus;
import com.eloarena.model.Player;
import com.eloarena.repository.BattleRepository;
import com.eloarena.repository.PlayerRepository;

@Service
public class BattleService {
    private final BattleRepository battleRepository;
    private final PlayerRepository playerRepository;
    private final EloService eloService;

    public BattleService(
            BattleRepository battleRepository,
            PlayerRepository playerRepository,
            EloService eloService) {
        this.battleRepository = battleRepository;
        this.playerRepository = playerRepository;
        this.eloService = eloService;
    }

    public Battle findById(Long battleId) {
        return battleRepository.findById(battleId).orElseThrow(() -> new BattleNotFoundException(battleId));
    }

    @Transactional
    public Battle reportResult(Long battleId, Long winnerId) {
        Battle battle = findById(battleId);

        if (battle.getStatus() != BattleStatus.PENDING) {
            throw new InvalidBattleResultException("Battle already finished: " + battleId);
        }

        if (!winnerId.equals(battle.getPlayerAId()) && !winnerId.equals(battle.getPlayerBId())) {
            throw new InvalidBattleResultException("Winner is not part of this battle: " + winnerId);
        }

        Player playerA = getPlayer(battle.getPlayerAId());
        Player playerB = getPlayer(battle.getPlayerBId());

        boolean winnerIsA = winnerId.equals(playerA.getId());
        int[] newRatings = eloService.updateRatings(playerA.getRating(), playerB.getRating(), winnerIsA);

        int deltaElo = Math.abs(newRatings[0] - playerA.getRating());

        applyResult(playerA, newRatings[0]);
        applyResult(playerB, newRatings[1]);

        battle.setWinnerId(winnerId);
        battle.setDeltaElo(deltaElo);
        battle.setStatus(BattleStatus.FINISHED);

        return battle;
    }

    private Player getPlayer(Long playerId) {
        return playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    private void applyResult(Player player, int newRating) {
        player.setRating(newRating);
        player.setGamesPlayed(player.getGamesPlayed() + 1);
    }

}
