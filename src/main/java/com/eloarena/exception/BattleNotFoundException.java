package com.eloarena.exception;

public class BattleNotFoundException extends RuntimeException {
    public BattleNotFoundException(Long battleId) {
        super("Battle not found: " + battleId);
    }
}
