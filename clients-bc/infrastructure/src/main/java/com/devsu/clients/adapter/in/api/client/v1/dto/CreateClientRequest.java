package com.devsu.clients.adapter.in.api.client.v1.dto;

import com.devsu.clients.kernel.model.enums.ClientStatus;
import com.devsu.clients.kernel.model.enums.DocumentType;
import com.devsu.clients.kernel.model.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record CreateClientRequest(
        @NotBlank String password,
        @NotBlank ClientStatus status,
        @NotBlank String name,
        @NotBlank String lastName,
        @NotNull  Gender gender,
        @NotNull  OffsetDateTime birthDate,
        @NotNull DocumentType documentType,
        @NotBlank String documentNumber,
        String address,
        String phone
) {
}
