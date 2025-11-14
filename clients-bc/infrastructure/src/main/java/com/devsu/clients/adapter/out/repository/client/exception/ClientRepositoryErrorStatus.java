package com.devsu.clients.adapter.out.repository.client.exception;

public enum ClientRepositoryErrorStatus  {
    CLI_100("Connection error"),
    CLI_101("Client not found"),
    CLI_102("Error saving client"),
    CLI_103("Error deleting client"),
    CLI_104("Error getting client"),
    CLI_105("Error getting clients");

    private final String message;

    ClientRepositoryErrorStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
