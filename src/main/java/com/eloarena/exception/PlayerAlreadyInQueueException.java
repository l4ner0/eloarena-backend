package com.eloarena.exception;

public class PlayerAlreadyInQueueException extends RuntimeException {
    public PlayerAlreadyInQueueException(Long playerId) {
        super("Player already in queue: " + playerId);
    }
}
