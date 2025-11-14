package com.devsu.clients.port.publisher;

import com.devsu.clients.kernel.event.AccountCreationRequested;
import reactor.core.publisher.Mono;

public interface ClientEventPublisherPort {
    Mono<AccountCreationRequested> publishAccountCreationRequested(AccountCreationRequested accountCreationRequested);
}
