package com.eloarena.exception;

public class InvalidBattleResultException extends RuntimeException {
    public InvalidBattleResultException(String message) {
        super(message);
    }
}