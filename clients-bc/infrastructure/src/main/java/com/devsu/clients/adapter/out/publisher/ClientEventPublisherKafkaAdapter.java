package com.devsu.clients.adapter.out.publisher;


import com.devsu.clients.adapter.out.publisher.dto.AccountCreationRequestedEvent;
import com.devsu.clients.adapter.out.publisher.dto.DomainEvent;
import com.devsu.clients.kernel.event.AccountCreationRequested;
import com.devsu.clients.port.publisher.ClientEventPublisherPort;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;

public class ClientEventPublisherKafkaAdapter implements ClientEventPublisherPort {

    private final String topicName;
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

    public ClientEventPublisherKafkaAdapter(
            String topicName,
            KafkaTemplate<String, DomainEvent> kafkaTemplate
    ) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public Mono<AccountCreationRequested> publishAccountCreationRequested(AccountCreationRequested accountCreationRequested) {
         return Mono.just(accountCreationRequested)
                .map(AccountCreationRequestedEvent::new)
                 .flatMap(it -> Mono.fromFuture(kafkaTemplate.send(topicName, it).toCompletableFuture()))
                 .map(_ -> accountCreationRequested);
    }
}
