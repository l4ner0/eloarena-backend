package com.eloarena.model;

import java.time.Instant;

public class Battle {
    private Long id;

    private Long playerAId;

    private Long playerBId;

    private Long winnerId;

    private int deltaElo;

    private BattleStatus status = BattleStatus.PENDING;

    private Instant createdAt = Instant.now();

    public Battle() {
    }

    public Battle(Long playerAId, Long playerBId) {
        this.playerAId = playerAId;
        this.playerBId = playerBId;
    }

    public Long getId() {
        return id;
    }

    public Long getPlayerAId() {
        return playerAId;
    }

    public Long getPlayerBId() {
        return playerBId;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public int getDeltaElo() {
        return deltaElo;
    }

    public void setDeltaElo(int deltaElo) {
        this.deltaElo = deltaElo;
    }

    public BattleStatus getStatus() {
        return status;
    }

    public void setStatus(BattleStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}
