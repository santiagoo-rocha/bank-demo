package com.devsu.clients.usecase.client;

import com.devsu.clients.kernel.model.Client;
import com.devsu.clients.kernel.query.GetClientByIdQuery;
import com.devsu.clients.port.repository.ClientRepositoryPort;
import com.devsu.clients.usecase.UseCase;
import reactor.core.publisher.Mono;

public class GetClientByIdUseCase implements UseCase<GetClientByIdQuery, Mono<Client>> {

    private final ClientRepositoryPort clientRepositoryPort;

    public GetClientByIdUseCase(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Mono<Client> execute(GetClientByIdQuery command) {
        return clientRepositoryPort.getClient(command.clientId());
    }
}
