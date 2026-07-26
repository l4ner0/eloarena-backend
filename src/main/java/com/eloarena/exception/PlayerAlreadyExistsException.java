package com.eloarena.exception;

public class PlayerAlreadyExistsException extends RuntimeException {
    public PlayerAlreadyExistsException(String name) {
        super("Player already exists: " + name);
    }
}
