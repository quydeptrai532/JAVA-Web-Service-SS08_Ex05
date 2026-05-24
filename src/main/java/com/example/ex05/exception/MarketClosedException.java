package com.example.ex05.exception;

public class MarketClosedException extends RuntimeException {
    public MarketClosedException() {
    }
    public MarketClosedException(String message) {
        super(message);
    }
}
