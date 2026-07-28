package com.eloarena.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eloarena.exception.PlayerAlreadyInQueueException;
import com.eloarena.exception.PlayerNotFoundException;
import com.eloarena.exception.PlayerNotInQueueException;
import com.eloarena.model.Battle;
import com.eloarena.model.Player;
import com.eloarena.repository.BattleRepository;
import com.eloarena.repository.PlayerRepository;

@Service
public class MatchmakingService {
    private final List<MatchTicket> queue = Collections.synchronizedList(new ArrayList<>());

    private final PlayerRepository playerRepository;
    private final BattleRepository battleRepository;

    public MatchmakingService(PlayerRepository playerRepository, BattleRepository battleRepository) {
        this.playerRepository = playerRepository;
        this.battleRepository = battleRepository;
    }

    public void enqueue(Long playerId) {
        synchronized (queue) {
            boolean alreadyQueued = queue.stream().anyMatch(t -> t.playerId().equals(playerId));
            if (alreadyQueued) {
                throw new PlayerAlreadyInQueueException(playerId);
            }

            Player player = playerRepository.findById(playerId)
                    .orElseThrow(() -> new PlayerNotFoundException(playerId));

            queue.add(new MatchTicket(playerId, player.getRating(), Instant.now()));
        }
    }

    public void dequeue(Long playerId) {
        synchronized (queue) {
            boolean remove = queue.removeIf(t -> t.playerId().equals(playerId));
            if (!remove) {
                throw new PlayerNotInQueueException(playerId);
            }
        }
    }

    public boolean isQueued(Long playerId) {
        synchronized (queue) {
            return queue.stream().anyMatch(t -> t.playerId().equals(playerId));
        }
    }

    public void matchmakingJob() {
        synchronized (queue) {
            for (int i = 0; i < queue.size(); i++) {
                MatchTicket ticketA = queue.get(i);
                int rangeA = acceptableRange(ticketA.enqueuedAt());

                for (int j = i + 1; j < queue.size(); j++) {
                    MatchTicket ticketB = queue.get(j);
                    int rangeB = acceptableRange(ticketB.enqueuedAt());
                    int diff = Math.abs(ticketA.rating() - ticketB.rating());

                    if (diff <= rangeA && diff <= rangeB) {
                        battleRepository.save(new Battle(ticketA.playerId(), ticketB.playerId()));
                        queue.remove(j);
                        queue.remove(i);
                        return;
                    }
                }
            }
        }
    }

    private int acceptableRange(Instant equeuedAt) {
        long waitedSeconds = Duration.between(equeuedAt, Instant.now()).getSeconds();
        if (waitedSeconds < 10)
            return 100;
        if (waitedSeconds < 20)
            return 200;
        return 400;
    }
}
