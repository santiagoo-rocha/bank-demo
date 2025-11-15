package com.devsu.accounts.adapter.out.repository.movement.exception;

public enum MovementRepositoryErrorStatus {
    ACC_301("Movement not found"),
    ACC_302("Error saving movement"),
    ACC_303("Error getting movement"),
    ACC_304("Error getting movements");

    private final String message;

    MovementRepositoryErrorStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
