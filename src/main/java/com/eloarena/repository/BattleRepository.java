package com.eloarena.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eloarena.model.Battle;
import com.eloarena.model.BattleStatus;

public interface BattleRepository extends JpaRepository<Battle, Long> {
    @Query("SELECT b FROM Battle b WHERE b.status = :status AND (b.playerAId = :playerId OR b.playerBId = :playerId)")
    Optional<Battle> findPendingBattleForPlayer(@Param("status") BattleStatus status, @Param("playerId") Long playerId);
}
