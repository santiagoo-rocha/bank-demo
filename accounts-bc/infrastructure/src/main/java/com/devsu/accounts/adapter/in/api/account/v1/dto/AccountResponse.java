package com.devsu.accounts.adapter.in.api.account.v1.dto;

import java.math.BigDecimal;

public record AccountResponse(
        String clientId,
        String accountId,
        String accountType,
        BigDecimal balance
){
}
