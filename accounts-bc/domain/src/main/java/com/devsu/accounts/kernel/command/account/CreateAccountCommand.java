package com.devsu.accounts.kernel.command.account;

import com.devsu.accounts.kernel.model.enums.AccountType;

import java.math.BigDecimal;

public record CreateAccountCommand(
        String clientId,
        AccountType accountType,
        BigDecimal initialDeposit
) {
}
