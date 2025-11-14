package com.devsu.clients.adapter.in.api.client.v1.dto;

import com.devsu.clients.kernel.model.enums.ClientStatus;
import com.devsu.clients.kernel.model.enums.DocumentType;
import com.devsu.clients.kernel.model.enums.Gender;

import java.time.OffsetDateTime;

public record ClientResponse(
        String clientId,
        ClientStatus status,
        String name,
        String lastName,
        Gender gender,
        OffsetDateTime birthDate,
        DocumentType documentType,
        String documentNumber,
        String address,
        String phone
) {}
