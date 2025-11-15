package com.devsu.accounts.adapter.in.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EventDispatcherListener {
    private final Logger log = LoggerFactory.getLogger(EventDispatcherListener.class);

    private final ObjectMapper objectMapper;
    private final Map<String, EventHandler<?>> handlers;

    public EventDispatcherListener(ObjectMapper objectMapper, Map<String, EventHandler<?>> handlers) {
        this.objectMapper = objectMapper;
        this.handlers = handlers;
    }

    @KafkaListener(
            topics = "${app.kafka.topics}"
    )
    public void onMessage(String raw) throws Exception {
        dispatch(raw)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(Throwable::printStackTrace)
                .onErrorResume(e -> Mono.empty())
                .subscribe();
    }

    public Mono<Void> dispatch(String raw) {
        log.info("Message received: {}", raw);
        return Mono.fromCallable(() -> objectMapper.readTree(raw))
                .flatMap(it -> {
                    String eventType = it.path("eventType").asText(null);
                    JsonNode payloadNode = it.path("payload");
                    EventHandler<?> handler = handlers.get(eventType);

                    Class<?> payloadType = handler.eventType();

                    return Mono.fromCallable(() -> objectMapper.treeToValue(payloadNode, payloadType))
                            .flatMap(payload -> ((EventHandler<Object>) handler).execute(payload));
                });
    }
}
