package com.devsu.accounts.adapter.out.repository.account;

import com.devsu.accounts.adapter.out.repository.account.mapper.AccountRepositoryMapper;
import com.devsu.accounts.adapter.out.repository.account.exception.AccountRepositoryException;
import com.devsu.accounts.kernel.model.Account;
import com.devsu.accounts.kernel.model.Page;
import com.devsu.accounts.port.repository.account.AccountRepositoryPort;
import com.devsu.accounts.port.repository.account.dto.GetAccountRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class AccountRepositoryAdapter implements AccountRepositoryPort {
    private final Logger log = LoggerFactory.getLogger(AccountRepositoryAdapter.class);

    private final SpringDataAccountRepository accountRepository;

    public AccountRepositoryAdapter(SpringDataAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Mono<Page<Account>> getAllAccounts(GetAccountRequest getAccountRequest) {
        return Mono.fromCallable(() -> accountRepository.findAll(
                PageRequest.of(getAccountRequest.page(), getAccountRequest.size())))
                .subscribeOn(Schedulers.boundedElastic())
                .map(it -> Page.<Account>builder()
                        .elements(it.map(AccountRepositoryMapper::toAccount).toList())
                        .page(getAccountRequest.page())
                        .size(getAccountRequest.size())
                        .totalElements(it.getTotalElements())
                        .build()
                )
                .doOnError(ex -> log.error("Error getting account, err {}", ex.getMessage()))
                .onErrorMap(_ -> AccountRepositoryException.errorGettingAccounts());
    }

    @Override
    public Mono<Account> getAccountById(String accountId) {
        return Mono.fromCallable(() -> accountRepository.findById(accountId))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(Mono::justOrEmpty)
                .map(AccountRepositoryMapper::toAccount)
                .doOnError(it -> log.error("Error getting account with id {} err {}", accountId, it.getMessage()))
                .onErrorMap(_ -> AccountRepositoryException.errorGettingAccountById())
                .switchIfEmpty(Mono.error(AccountRepositoryException.notFoundError()));
    }

    @Override
    public Mono<Account> saveAccount(Account account) {
        return Mono.just(account)
                .map(AccountRepositoryMapper::toEntity)
                .flatMap(it -> Mono.fromCallable(() -> accountRepository.save(it))
                        .subscribeOn(Schedulers.boundedElastic()))
                .map(AccountRepositoryMapper::toAccount)
                .doOnError(it -> log.error("Error saving account with id {} err {}", account.accountId(), it.getMessage()))
                .onErrorMap(_ -> AccountRepositoryException.errorSavingAccount());
    }
}
