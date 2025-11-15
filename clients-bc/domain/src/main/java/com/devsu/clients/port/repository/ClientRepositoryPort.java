package com.devsu.clients.port.repository;

import com.devsu.clients.kernel.model.Client;
import com.devsu.clients.kernel.model.Page;
import com.devsu.clients.port.repository.dto.GetClientsRequest;
import reactor.core.publisher.Mono;

public interface ClientRepositoryPort {
    Mono<Page<Client>> getAllClients(GetClientsRequest getClientsRequest);
    Mono<Client> getClientById(String clientId);
    Mono<Client> saveClient(Client client);
    Mono<Void> deleteClient(String clientId);
}
