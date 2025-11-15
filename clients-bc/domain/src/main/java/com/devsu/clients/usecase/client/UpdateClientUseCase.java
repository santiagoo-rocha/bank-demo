package com.devsu.clients.usecase.client;

import com.devsu.clients.kernel.command.client.UpdateClientCommand;
import com.devsu.clients.kernel.model.Client;
import com.devsu.clients.port.repository.ClientRepositoryPort;
import com.devsu.clients.usecase.UseCase;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class UpdateClientUseCase implements UseCase<UpdateClientCommand, Mono<Client>> {

    private final ClientRepositoryPort clientRepositoryPort;

    public UpdateClientUseCase(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Mono<Client> execute(UpdateClientCommand command) {
        return clientRepositoryPort.getClientById(command.clientId())
                .map(it -> updateClient(command, it))
                .flatMap(clientRepositoryPort::saveClient);
    }

    private Client updateClient(UpdateClientCommand command, Client client) {
        return client.toBuilder()
                .password(Optional.ofNullable(command.password()).orElse(client.getPassword()))
                .name(Optional.ofNullable(command.name()).orElse(client.getName()))
                .gender(Optional.ofNullable(command.gender()).orElse(client.getGender()))
                .lastName(Optional.ofNullable(command.lastName()).orElse(client.getLastName()))
                .address(Optional.ofNullable(command.address()).orElse(client.getAddress()))
                .phone(Optional.ofNullable(command.phone()).orElse(client.getPhone()))
                .build();
    }
}
