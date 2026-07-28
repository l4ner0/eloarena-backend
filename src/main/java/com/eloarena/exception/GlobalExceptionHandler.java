package com.eloarena.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlayerAlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExists(PlayerAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(PlayerAlreadyInQueueException.class)
    public ResponseEntity<String> handleAlreadyInQueue(PlayerAlreadyInQueueException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(PlayerNotInQueueException.class)
    public ResponseEntity<String> handleNotInQueue(PlayerNotInQueueException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<String> handleNotFound(PlayerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PlayerInBattleException.class)
    public ResponseEntity<String> handleInBattle(PlayerInBattleException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(BattleNotFoundException.class)
    public ResponseEntity<String> handleBattleNotFound(BattleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidBattleResultException.class)
    public ResponseEntity<String> handleInvalidResult(InvalidBattleResultException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}