package com.devsu.clients.usecase.account;

import com.devsu.clients.kernel.command.account.CreateAccountCommand;
import com.devsu.clients.kernel.event.AccountCreationRequested;
import com.devsu.clients.port.publisher.ClientEventPublisherPort;
import com.devsu.clients.port.repository.ClientRepositoryPort;
import com.devsu.clients.usecase.UseCase;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class CreateAccountUseCase implements UseCase<CreateAccountCommand, Mono<String>> {

    private final ClientRepositoryPort clientRepositoryPort;
    private final ClientEventPublisherPort clientEventPublisherPort;

    public CreateAccountUseCase(ClientRepositoryPort clientRepositoryPort, ClientEventPublisherPort clientEventPublisherPort) {
        this.clientRepositoryPort = clientRepositoryPort;
        this.clientEventPublisherPort = clientEventPublisherPort;
    }

    @Override
    public Mono<String> execute(CreateAccountCommand command) {
        return clientRepositoryPort.getClientById(command.clientId())
                .map(_ -> buildEvent(command))
                .flatMap(clientEventPublisherPort::publishAccountCreationRequested)
                .map(AccountCreationRequested::accountId);
    }

    private AccountCreationRequested buildEvent(CreateAccountCommand command){
        return new AccountCreationRequested(
                UUID.randomUUID().toString(),
                command.clientId(),
                command.accountType(),
                command.initialDeposit()
        );
    }
}
