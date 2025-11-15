package com.devsu.accounts.config;

import com.devsu.accounts.port.repository.account.AccountRepositoryPort;
import com.devsu.accounts.usecase.account.CreateAccountUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfiguration {

    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepositoryPort accountRepositoryPort) {
        return new CreateAccountUseCase(accountRepositoryPort);
    }

}
