package com.devsu.accounts.config;

import com.devsu.accounts.adapter.out.repository.account.AccountRepositoryAdapter;
import com.devsu.accounts.adapter.out.repository.account.SpringDataAccountRepository;
import com.devsu.accounts.port.repository.account.AccountRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresConfig {
    @Bean
    public AccountRepositoryPort clientRepository(SpringDataAccountRepository clientRepository) {
        return new AccountRepositoryAdapter(clientRepository);
    }
}
