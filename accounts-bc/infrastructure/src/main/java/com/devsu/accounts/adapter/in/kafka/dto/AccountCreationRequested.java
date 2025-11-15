package com.devsu.accounts.adapter.in.kafka.dto;

import com.devsu.accounts.kernel.model.enums.AccountType;

import java.math.BigDecimal;

public record AccountCreationRequested(
        String clientId,
        AccountType accountType,
        BigDecimal initialDeposit
) {
}
