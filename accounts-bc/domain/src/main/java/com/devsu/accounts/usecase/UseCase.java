package com.devsu.accounts.usecase;

@FunctionalInterface
public interface UseCase<T, R> {
    R execute(T command);
}

