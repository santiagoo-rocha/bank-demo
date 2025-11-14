package com.devsu.clients.adapter.out.repository.client;

import com.devsu.clients.adapter.out.repository.client.mapper.ClientRepositoryMapper;
import com.devsu.clients.adapter.out.repository.client.exception.ClientRepositoryException;
import com.devsu.clients.kernel.model.Client;
import com.devsu.clients.kernel.model.Page;
import com.devsu.clients.port.repository.ClientRepositoryPort;
import com.devsu.clients.port.repository.dto.GetClientsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ClientRepositoryAdapter implements ClientRepositoryPort {
    private final Logger log = LoggerFactory.getLogger(ClientRepositoryAdapter.class);

    private final SpringDataClientRepository clientRepository;

    public ClientRepositoryAdapter(SpringDataClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Mono<Page<Client>> getAllClients(GetClientsRequest getClientsRequest) {
        return Mono.fromCallable(() -> clientRepository.findAll(
                PageRequest.of(getClientsRequest.page(), getClientsRequest.size())))
                .subscribeOn(Schedulers.boundedElastic())
                .map(it -> Page.<Client>builder()
                        .elements(it.map(ClientRepositoryMapper::toClient).toList())
                        .page(getClientsRequest.page())
                        .size(getClientsRequest.size())
                        .totalElements(it.getTotalElements())
                        .build()
                )
                .doOnError(ex -> log.error("Error getting clients, err{}", ex.getMessage()))
                .onErrorMap(_ -> ClientRepositoryException.errorGettingClients());
    }

    @Override
    public Mono<Client> getClient(String clientId) {
        return Mono.fromCallable(() -> clientRepository.findById(clientId))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(Mono::justOrEmpty)
                .map(ClientRepositoryMapper::toClient)
                .doOnError(it -> log.error("Error getting client with id {} err {}", clientId, it.getMessage()))
                .onErrorMap(_ -> ClientRepositoryException.errorGettingClientById())
                .switchIfEmpty(Mono.error(ClientRepositoryException.notFoundError()));
    }

    @Override
    public Mono<Client> saveClient(Client client) {
        return Mono.just(client)
                .map(ClientRepositoryMapper::toEntity)
                .flatMap(it -> Mono.fromCallable(() -> clientRepository.save(it))
                        .subscribeOn(Schedulers.boundedElastic()))
                .map(ClientRepositoryMapper::toClient)
                .doOnError(it -> log.error("Error saving client with id {} err {}", client.getClientId(), it.getMessage()))
                .onErrorMap(_ -> ClientRepositoryException.errorSavingClient());
    }

    @Override
    public Mono<Void> deleteClient(String clientId) {
        return Mono.fromRunnable(() -> clientRepository.deleteById(clientId))
                .subscribeOn(Schedulers.boundedElastic())
                .then()
                .doOnError(it -> log.error("Error deleting client with id {} err {}", clientId, it.getMessage()))
                .onErrorMap(_ -> ClientRepositoryException.errorDeletingClient());
    }
}
