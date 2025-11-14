package com.devsu.clients.usecase.client;

import com.devsu.clients.kernel.command.client.DeleteClientCommand;
import com.devsu.clients.port.repository.ClientRepositoryPort;
import com.devsu.clients.usecase.UseCase;
import reactor.core.publisher.Mono;

public class DeleteClientUseCase implements UseCase<DeleteClientCommand, Mono<Void>> {

    private final ClientRepositoryPort clientRepositoryPort;

    public DeleteClientUseCase(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Mono<Void> execute(DeleteClientCommand command) {
        return clientRepositoryPort.deleteClient(command.clientId());
    }
}
