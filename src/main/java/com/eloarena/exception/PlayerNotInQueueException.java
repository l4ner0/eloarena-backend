package com.eloarena.exception;

public class PlayerNotInQueueException extends RuntimeException {
    public PlayerNotInQueueException(Long playerId) {
        super("Player not in queue: " + playerId);
    }
}
