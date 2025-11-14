package com.devsu.clients.adapter.in.api.account.v1.mapper;

import com.devsu.clients.adapter.in.api.account.v1.dto.AccountCreationResponse;
import com.devsu.clients.adapter.in.api.account.v1.dto.CreateAccountRequest;
import com.devsu.clients.kernel.command.account.CreateAccountCommand;

public class AccountClientMapper {

    public static CreateAccountCommand toCreateAccountCommand(String clientId, CreateAccountRequest createAccountRequest) {
        return new CreateAccountCommand(clientId, createAccountRequest.accountType(), createAccountRequest.initialDeposit());
    }

    public static AccountCreationResponse toAccountCreationResponse(String accountId, String clientId, CreateAccountRequest createAccountRequest) {
        return new AccountCreationResponse(accountId, clientId, createAccountRequest.accountType(), createAccountRequest.initialDeposit());
    }
}
