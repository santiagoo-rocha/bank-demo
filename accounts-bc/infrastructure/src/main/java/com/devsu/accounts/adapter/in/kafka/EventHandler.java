package com.devsu.accounts.adapter.in.kafka;

import reactor.core.publisher.Mono;

public interface EventHandler<T> {
    Class<T> eventType();
    Mono<Void> execute(T payload);

}
