package com.eloarena.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eloarena.exception.PlayerAlreadyExistsException;
import com.eloarena.model.Player;
import com.eloarena.repository.PlayerRepository;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player create(String name) {
        if (playerRepository.findByName(name).isPresent()) {
            throw new PlayerAlreadyExistsException(name);
        }

        return playerRepository.save(new Player(name));
    }

    public List<Player> listAllOrderedByRating() {
        return playerRepository.findAllByOrderByRatingDesc();
    }
}
