package com.eloarena.exception;

public class PlayerInBattleException extends RuntimeException {
    public PlayerInBattleException(Long playerId) {
        super("Player already has a pending battle: " + playerId);
    }
}
