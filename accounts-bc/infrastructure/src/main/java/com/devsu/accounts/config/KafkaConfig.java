package com.devsu.accounts.config;

import com.devsu.accounts.adapter.in.kafka.AccountCreationHandler;
import com.devsu.accounts.adapter.in.kafka.EventDispatcherListener;
import com.devsu.accounts.adapter.in.kafka.EventHandler;
import com.devsu.accounts.usecase.account.CreateAccountUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topics}")
    private String topicName;

    @Bean
    public EventDispatcherListener eventDispatcherListener(
            ObjectMapper objectMapper,
            Map<String, EventHandler<?>> handlers

    ) {
        return new EventDispatcherListener(objectMapper, handlers);
    }

    @Bean("AccountCreationRequested")
    public AccountCreationHandler accountCreationHandler(CreateAccountUseCase createAccountUseCase){
        return new AccountCreationHandler(createAccountUseCase);
    }
}
