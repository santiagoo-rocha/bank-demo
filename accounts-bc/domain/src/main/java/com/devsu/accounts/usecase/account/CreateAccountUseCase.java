package com.devsu.accounts.usecase.account;

import com.devsu.accounts.kernel.command.account.CreateAccountCommand;
import com.devsu.accounts.kernel.model.Account;
import com.devsu.accounts.kernel.model.enums.AccountStatus;
import com.devsu.accounts.port.repository.account.AccountRepositoryPort;
import com.devsu.accounts.usecase.UseCase;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class CreateAccountUseCase implements UseCase<CreateAccountCommand, Mono<Account>> {

    private final AccountRepositoryPort accountRepositoryPort;

    public CreateAccountUseCase(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Mono<Account> execute(CreateAccountCommand command) {
        return Mono.just(buildAccount(command))
                .flatMap(accountRepositoryPort::saveAccount);
    }

    private Account buildAccount(CreateAccountCommand command){
        return Account.builder()
                .accountId(UUID.randomUUID().toString())
                .clientId(command.clientId())
                .type(command.accountType())
                .balance(command.initialDeposit())
                .status(AccountStatus.ENABLED)
                .build();
    }
}
