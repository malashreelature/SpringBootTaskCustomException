package com.example.SpringBoot.service.exception;

public class BadCredentialsException extends Exception {
    public BadCredentialsException(String message) {
        super(message);
    }
}
