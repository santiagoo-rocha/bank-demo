package com.devsu.clients.kernel.command.client;

import com.devsu.clients.kernel.model.enums.DocumentType;
import com.devsu.clients.kernel.model.enums.Gender;

import java.time.OffsetDateTime;

public record CreateClientCommand(
        String password,
        String name,
        String lastName,
        Gender gender,
        OffsetDateTime birthDate,
        DocumentType documentType,
        String documentNumber,
        String address,
        String phone
) {}
