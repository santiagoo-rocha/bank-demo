package com.devsu.accounts.kernel.event;

import java.math.BigDecimal;

public record AccountCreationRequested(
        String accountId,
        String clientId,
        String accountType,
        BigDecimal initialDeposit
) {
}
