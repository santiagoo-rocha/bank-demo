package com.devsu.accounts.usecase.movement;

import com.devsu.accounts.kernel.command.movement.CreateMovementCommand;
import com.devsu.accounts.kernel.exception.BusinessException;
import com.devsu.accounts.kernel.model.Account;
import com.devsu.accounts.kernel.model.Movement;
import com.devsu.accounts.port.repository.account.AccountRepositoryPort;
import com.devsu.accounts.port.repository.movement.MovementRepositoryPort;
import com.devsu.accounts.usecase.UseCase;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class CreateMovementUseCase implements UseCase<CreateMovementCommand, Mono<Movement>> {

    private final AccountRepositoryPort accountRepositoryPort;
    private final MovementRepositoryPort movementRepositoryPort;

    public CreateMovementUseCase(AccountRepositoryPort accountRepositoryPort, MovementRepositoryPort movementRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.movementRepositoryPort = movementRepositoryPort;
    }

    @Override
    public Mono<Movement> execute(CreateMovementCommand command) {
        return accountRepositoryPort.getAccountById(command.accountId())
                .filter(it -> hasFunds(it, command))
                .switchIfEmpty(Mono.error(BusinessException.notEnoughFunds()))
                .flatMap(it -> saveMovement(it, command));

    }

    public boolean hasFunds(Account account, CreateMovementCommand command) {
        return command.value().compareTo(BigDecimal.ZERO) > 0 ||
                (command.value().compareTo(BigDecimal.ZERO) < 0 &&
                account.balance().compareTo(command.value().abs()) >= 0);
    }

    public Mono<Movement> saveMovement(Account account, CreateMovementCommand command) {
        return Mono.just(createMovement(account.accountId(), command))
                .flatMap(movementRepositoryPort::saveMovement)
                .flatMap(it -> updateAccountBalance(account, it));
    }

    public Movement createMovement(String accountId, CreateMovementCommand command) {
        return new Movement(UUID.randomUUID().toString(), accountId, command.type(),
                OffsetDateTime.now(), command.value());
    }

    private Mono<Movement> updateAccountBalance(Account account, Movement movement) {
        return Mono.just(account)
                .map(it -> it.balance().add(movement.value()))
                .map(it -> account.toBuilder().balance(it).build())
                .flatMap(accountRepositoryPort::saveAccount)
                .thenReturn(movement);
    }

}
