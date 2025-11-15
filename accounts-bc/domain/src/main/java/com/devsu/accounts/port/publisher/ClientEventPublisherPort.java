package com.devsu.accounts.port.publisher;

import com.devsu.accounts.kernel.event.AccountCreationRequested;
import reactor.core.publisher.Mono;

public interface ClientEventPublisherPort {
    Mono<AccountCreationRequested> publishAccountCreationRequested(AccountCreationRequested accountCreationRequested);
}
