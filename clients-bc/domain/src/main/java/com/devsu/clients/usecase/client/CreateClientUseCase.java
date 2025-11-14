package com.devsu.clients.usecase.client;

import com.devsu.clients.kernel.command.client.CreateClientCommand;
import com.devsu.clients.kernel.model.Client;
import com.devsu.clients.kernel.model.enums.ClientStatus;
import com.devsu.clients.port.repository.ClientRepositoryPort;
import com.devsu.clients.usecase.UseCase;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class CreateClientUseCase implements UseCase<CreateClientCommand, Mono<Client>> {

    private final ClientRepositoryPort clientRepositoryPort;

    public CreateClientUseCase(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Mono<Client> execute(CreateClientCommand command) {
        return Mono.just(buildClient(command))
                .flatMap(clientRepositoryPort::saveClient);
    }

    private Client buildClient(CreateClientCommand command) {
        return Client.builder()
                .clientId(UUID.randomUUID().toString())
                .password(command.password())
                .status(ClientStatus.ENABLE)
                .name(command.name())
                .gender(command.gender())
                .lastName(command.lastName())
                .birthDate(command.birthDate())
                .documentNumber(command.documentNumber())
                .documentType(command.documentType())
                .address(command.address())
                .phone(command.phone())
                .build();
    }
}
