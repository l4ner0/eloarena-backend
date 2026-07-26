package com.eloarena.repository;

import com.eloarena.model.Player;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByName(String name);

    List<Player> findAllByOrderByRatingDesc();
}
