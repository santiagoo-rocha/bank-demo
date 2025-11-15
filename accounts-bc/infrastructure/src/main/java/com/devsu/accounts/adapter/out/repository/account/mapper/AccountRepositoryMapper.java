package com.devsu.accounts.adapter.out.repository.account.mapper;

import com.devsu.accounts.adapter.out.repository.account.dto.AccountEntity;
import com.devsu.accounts.kernel.model.Account;

public class AccountRepositoryMapper {

    public static Account toAccount(AccountEntity entity){
        return Account.builder()
                .accountId(entity.getId())
                .clientId(entity.getClientId())
                .accountNumber(entity.getAccountNumber())
                .type(entity.getType())
                .balance(entity.getBalance())
                .status(entity.getStatus())
                .build();
    }

    public static AccountEntity toEntity(Account account){
        AccountEntity entity = new AccountEntity();
        entity.setId(account.accountId());
        entity.setClientId(account.clientId());
        entity.setAccountNumber(account.accountNumber());
        entity.setType(account.type());
        entity.setBalance(account.balance());
        entity.setStatus(account.status());

        return entity;
    }
}
