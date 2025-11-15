package com.devsu.accounts.kernel.exception;

public enum ErrorStatus  {
    ACC_100("Not enough funds to complete transaction");

    private final String message;

    ErrorStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}