package com.devsu.clients.adapter.out.publisher.dto;

import com.devsu.clients.kernel.event.AccountCreationRequested;

import java.math.BigDecimal;

public class AccountCreationRequestedEvent extends DomainEvent{
    private static final String EVENT_TYPE = "AccountCreationRequested";

    private final String clientId;
    private final String accountType;
    private final BigDecimal initialDeposit;

    public AccountCreationRequestedEvent(AccountCreationRequested event) {
        super(EVENT_TYPE);
        this.clientId = event.clientId();
        this.accountType = event.accountType();
        this.initialDeposit = event.initialDeposit();
    }

    public String getClientId() {
        return clientId;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getInitialDeposit() {
        return initialDeposit;
    }
}
