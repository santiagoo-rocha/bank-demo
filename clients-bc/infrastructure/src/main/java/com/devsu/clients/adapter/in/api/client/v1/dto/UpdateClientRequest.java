package com.devsu.clients.adapter.in.api.client.v1.dto;

import com.devsu.clients.kernel.model.enums.Gender;

public record UpdateClientRequest(
        String password,
        String name,
        String lastName,
        Gender gender,
        String address,
        String phone
) {}
