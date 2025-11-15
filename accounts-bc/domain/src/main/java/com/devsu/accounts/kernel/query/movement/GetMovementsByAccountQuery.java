package com.devsu.accounts.kernel.query.movement;

public record GetMovementsByAccountQuery(
        String accountId, int page, int size
) {
}
