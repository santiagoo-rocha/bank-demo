package com.devsu.clients.adapter.in.api.account.v1.dto;

import java.math.BigDecimal;

public record AccountCreationResponse (
        String clientId,
        String accountId,
        String accountType,
        BigDecimal balance
){
}
