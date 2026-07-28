package com.eloarena.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eloarena.dto.SimulationResult;
import com.eloarena.exception.InvalidBattleResultException;
import com.eloarena.model.Battle;
import com.eloarena.model.BattleStatus;
import com.eloarena.model.Player;
import com.eloarena.repository.BattleRepository;
import com.eloarena.repository.PlayerRepository;

import jakarta.transaction.Transactional;

@Service
public class SimulationService {
    private final PlayerRepository playerRepository;
    private final BattleRepository battleRepository;
    private final EloService eloService;
    private final Random random = new Random();

    public SimulationService(
            PlayerRepository playerRepository,
            BattleRepository battleRepository,
            EloService eloService) {
        this.playerRepository = playerRepository;
        this.battleRepository = battleRepository;
        this.eloService = eloService;
    }

    @Transactional
    public SimulationResult simulate(int battles) {
        List<Player> players = playerRepository.findAll();
        if (players.size() < 2) {
            throw new InvalidBattleResultException("At least 2 players are required to simulate");
        }

        for (int i = 0; i < battles; i++) {
            Player playerA = players.get(random.nextInt(players.size()));
            Player playerB = pickOpponent(players, playerA);

            runBattle(playerA, playerB);
        }

        return new SimulationResult(battles, playerRepository.findAllByOrderByRatingDesc());
    }

    private Player pickOpponent(List<Player> players, Player playerA) {
        Player opponent;
        do {
            opponent = players.get(random.nextInt(players.size()));
        } while (opponent.getId().equals(playerA.getId()));

        return opponent;
    }

    private void runBattle(Player playerA, Player playerB) {
        double expectedA = eloService.expectedScore(playerA.getRating(), playerB.getRating());
        boolean winnerIsA = random.nextDouble() < expectedA;

        int[] newRatings = eloService.updateRatings(playerA.getRating(), playerB.getRating(), winnerIsA);
        int deltaElo = Math.abs(newRatings[0] - playerA.getRating());

        Battle battle = new Battle(playerA.getId(), playerB.getId());

        battle.setWinnerId(winnerIsA ? playerA.getId() : playerB.getId());
        battle.setDeltaElo(deltaElo);
        battle.setStatus(BattleStatus.FINISHED);
        battleRepository.save(battle);

        applyResult(playerA, newRatings[0]);
        applyResult(playerB, newRatings[1]);

    }

    private void applyResult(Player player, int newRating) {
        player.setRating(newRating);
        player.setGamesPlayed(player.getGamesPlayed() + 1);
    }

}
