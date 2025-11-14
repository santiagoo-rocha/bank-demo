package com.devsu.clients.config;

import com.devsu.clients.adapter.out.publisher.ClientEventPublisherKafkaAdapter;
import com.devsu.clients.adapter.out.publisher.dto.DomainEvent;
import com.devsu.clients.port.publisher.ClientEventPublisherPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topic-name}")
    private String topicName;

    @Bean
    public ClientEventPublisherPort clientEventPublisherPort(KafkaTemplate<String, DomainEvent> kafkaTemplate) {
        return new ClientEventPublisherKafkaAdapter(topicName, kafkaTemplate);
    }
}
