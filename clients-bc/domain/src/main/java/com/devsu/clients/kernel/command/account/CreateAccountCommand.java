package com.devsu.clients.kernel.command.account;

import java.math.BigDecimal;

public record CreateAccountCommand(
        String clientId,
        String accountType,
        BigDecimal initialDeposit
) {
}
