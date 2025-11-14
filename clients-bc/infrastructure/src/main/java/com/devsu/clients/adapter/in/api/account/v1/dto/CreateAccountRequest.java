package com.devsu.clients.adapter.in.api.account.v1.dto;

import java.math.BigDecimal;

public record CreateAccountRequest(
        String accountType,
        BigDecimal initialDeposit
) {}
