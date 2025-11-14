package com.devsu.clients.kernel.command.client;

import com.devsu.clients.kernel.model.enums.Gender;

public record UpdateClientCommand(
        String clientId,
        String password,
        String name,
        String lastName,
        Gender gender,
        String address,
        String phone
) {}

