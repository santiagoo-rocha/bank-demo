package com.devsu.clients.adapter.out.publisher.dto;

import jakarta.validation.Payload;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {
    private final String eventId;
    private final String eventType;
    private final Instant eventTime;

    public DomainEvent(String eventType) {
        this.eventId = UUID.randomUUID().toString();
        this.eventType = eventType;
        this.eventTime = Instant.now();
    }

    public Object payload(){
        return this;
    };

    public Instant getEventTime() {
        return eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventId() {
        return eventId;
    }
}
