package com.eloarena.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long playerAId;

    @Column(nullable = false)
    private Long playerBId;

    private Long winnerId;

    private int deltaElo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BattleStatus status = BattleStatus.PENDING;

    @Column(nullable = false)
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
