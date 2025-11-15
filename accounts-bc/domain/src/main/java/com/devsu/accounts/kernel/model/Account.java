package com.devsu.accounts.kernel.model;

import com.devsu.accounts.kernel.model.enums.AccountStatus;
import com.devsu.accounts.kernel.model.enums.AccountType;

import java.math.BigDecimal;

public record Account(
        String accountId,
        String clientId,
        String accountNumber,
        AccountType type,
        BigDecimal balance,
        AccountStatus status
) {

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return builder()
                .accountId(accountId)
                .accountNumber(accountNumber)
                .type(type)
                .balance(balance)
                .clientId(clientId)
                .status(status);
    }

    public static final class Builder {
        private String accountId;
        private String clientId;
        private String accountNumber;
        private AccountType type;
        private BigDecimal balance;
        private AccountStatus status;

        private Builder() {
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder type(AccountType type) {
            this.type = type;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder status(AccountStatus status) {
            this.status = status;
            return this;
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Account build() {
            return new Account(accountId, clientId, accountNumber, type, balance, status);
        }
    }
}
