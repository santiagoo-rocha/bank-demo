package com.devsu.accounts.adapter.out.repository.account.exception;

public enum AccountRepositoryErrorStatus {
    ACC_201("Account not found"),
    ACC_202("Error saving account"),
    ACC_203("Error getting account"),
    ACC_204("Error getting accounts");

    private final String message;

    AccountRepositoryErrorStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
