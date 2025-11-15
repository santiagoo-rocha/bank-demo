package com.devsu.accounts.adapter.out.repository.account.exception;

import com.devsu.accounts.kernel.exception.BusinessException;

public class AccountRepositoryException extends BusinessException {
    public AccountRepositoryException(AccountRepositoryErrorStatus errorStatus) {
        super(errorStatus.name(), errorStatus.getMessage());
    }

    public static AccountRepositoryException notFoundError() {
        return new AccountRepositoryException(AccountRepositoryErrorStatus.ACC_201);
    }

    public static AccountRepositoryException errorSavingAccount() {
        return new AccountRepositoryException(AccountRepositoryErrorStatus.ACC_202);
    }

    public static AccountRepositoryException errorGettingAccountById() {
        return new AccountRepositoryException(AccountRepositoryErrorStatus.ACC_203);
    }

    public static AccountRepositoryException errorGettingAccounts() {
        return new AccountRepositoryException(AccountRepositoryErrorStatus.ACC_204);
    }
}
