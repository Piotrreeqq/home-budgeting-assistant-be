package com.petke.budgeting.exception;

public class RegistryNotFoundException extends RuntimeException {

    public RegistryNotFoundException(String message) {
        super(message);
    }
}
