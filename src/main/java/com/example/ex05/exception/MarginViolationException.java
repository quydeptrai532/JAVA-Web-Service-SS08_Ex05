package com.example.ex05.exception;

public class MarginViolationException extends RuntimeException {
    public MarginViolationException(String message) {
        super(message);
    }
}
