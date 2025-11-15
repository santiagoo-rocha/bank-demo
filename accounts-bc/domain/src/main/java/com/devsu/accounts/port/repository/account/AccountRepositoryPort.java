package com.devsu.accounts.port.repository.account;

import com.devsu.accounts.kernel.model.Account;
import com.devsu.accounts.kernel.model.Page;
import com.devsu.accounts.port.repository.account.dto.GetAccountRequest;
import reactor.core.publisher.Mono;

public interface AccountRepositoryPort {
    Mono<Page<Account>> getAllAccounts(GetAccountRequest getAccountRequest);
    Mono<Account> getAccountById(String accountId);
    Mono<Account> saveAccount(Account account);
}
