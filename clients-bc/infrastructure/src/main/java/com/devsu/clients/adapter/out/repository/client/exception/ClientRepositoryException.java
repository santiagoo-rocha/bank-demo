package com.devsu.clients.adapter.out.repository.client.exception;

import com.devsu.clients.kernel.exception.BusinessException;

public class ClientRepositoryException extends BusinessException {
    public ClientRepositoryException(ClientRepositoryErrorStatus errorStatus) {
        super(errorStatus.name(), errorStatus.getMessage());
    }

    public static ClientRepositoryException errorGettingClients() {
        return new ClientRepositoryException(ClientRepositoryErrorStatus.CLI_100);
    }

    public static ClientRepositoryException notFoundError() {
        return new ClientRepositoryException(ClientRepositoryErrorStatus.CLI_101);
    }

    public static ClientRepositoryException errorSavingClient() {
        return new ClientRepositoryException(ClientRepositoryErrorStatus.CLI_102);
    }

    public static ClientRepositoryException errorDeletingClient() {
        return new ClientRepositoryException(ClientRepositoryErrorStatus.CLI_103);
    }

    public static ClientRepositoryException errorGettingClientById() {
        return new ClientRepositoryException(ClientRepositoryErrorStatus.CLI_104);
    }
}
