package com.devsu.clients.usecase.client;

import com.devsu.clients.kernel.model.Client;
import com.devsu.clients.kernel.model.Page;
import com.devsu.clients.kernel.query.GetClientsQuery;
import com.devsu.clients.port.repository.ClientRepositoryPort;
import com.devsu.clients.port.repository.dto.GetClientsRequest;
import com.devsu.clients.usecase.UseCase;
import reactor.core.publisher.Mono;

public class GetClientsUseCase implements UseCase<GetClientsQuery, Mono<Page<Client>>> {

    private final ClientRepositoryPort clientRepositoryPort;

    public GetClientsUseCase(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Mono<Page<Client>> execute(GetClientsQuery command) {
        return Mono.just(new GetClientsRequest(command.page(), command.size()))
                .flatMap(clientRepositoryPort::getAllClients);
    }
}
