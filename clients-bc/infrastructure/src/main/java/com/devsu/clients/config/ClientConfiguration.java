package com.devsu.clients.config;

import com.devsu.clients.port.repository.ClientRepositoryPort;
import com.devsu.clients.usecase.client.*;
import org.springframework.context.annotation.Bean;

public class ClientConfiguration {

    @Bean
    public GetClientsUseCase getClientsUseCase(ClientRepositoryPort clientRepositoryPort) {
        return new GetClientsUseCase(clientRepositoryPort);
    }

    @Bean
    public GetClientByIdUseCase getClientByIdUseCase(ClientRepositoryPort clientRepositoryPort) {
        return new GetClientByIdUseCase(clientRepositoryPort);
    }

    @Bean
    public CreateClientUseCase createClientUseCase(ClientRepositoryPort clientRepositoryPort) {
        return new CreateClientUseCase(clientRepositoryPort);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(ClientRepositoryPort clientRepositoryPort) {
        return new UpdateClientUseCase(clientRepositoryPort);
    }

    @Bean
    public DeleteClientUseCase deleteClientUseCase(ClientRepositoryPort clientRepositoryPort) {
        return new DeleteClientUseCase(clientRepositoryPort);
    }
}
