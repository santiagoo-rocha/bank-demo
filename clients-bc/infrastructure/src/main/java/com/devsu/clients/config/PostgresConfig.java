package com.devsu.clients.config;

import com.devsu.clients.adapter.out.repository.client.ClientRepositoryAdapter;
import com.devsu.clients.adapter.out.repository.client.SpringDataClientRepository;
import com.devsu.clients.port.repository.ClientRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresConfig {
    @Bean
    public ClientRepositoryPort clientRepository(SpringDataClientRepository clientRepository) {
        return new ClientRepositoryAdapter(clientRepository);
    }
}
