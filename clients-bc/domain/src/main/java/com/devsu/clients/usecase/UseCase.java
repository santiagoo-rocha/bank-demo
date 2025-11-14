package com.devsu.clients.usecase;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UseCase<T, R> {
    R execute(T command);
}

