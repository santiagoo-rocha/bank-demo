package com.devsu.accounts.adapter.in.kafka;

import com.devsu.accounts.adapter.in.kafka.dto.AccountCreationRequested;
import com.devsu.accounts.kernel.command.account.CreateAccountCommand;
import com.devsu.accounts.usecase.account.CreateAccountUseCase;
import reactor.core.publisher.Mono;

public class AccountCreationHandler implements EventHandler<AccountCreationRequested> {

    private final CreateAccountUseCase createAccountUseCase;

    public AccountCreationHandler(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    @Override
    public Class<AccountCreationRequested> eventType() {
        return AccountCreationRequested.class;
    }

    @Override
    public Mono<Void> execute(AccountCreationRequested payload) {
        return Mono.just(buildCreateAccountCommand(payload))
                .flatMap(createAccountUseCase::execute)
                .then();
    }

    public CreateAccountCommand buildCreateAccountCommand(AccountCreationRequested payload) {
        return new CreateAccountCommand(payload.clientId(), payload.accountType(), payload.initialDeposit());
    }
}
