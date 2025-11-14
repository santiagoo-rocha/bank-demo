package com.devsu.clients.adapter.in.api.client.v1.mapper;

import com.devsu.clients.adapter.in.api.dto.PageResponse;
import com.devsu.clients.kernel.command.client.CreateClientCommand;
import com.devsu.clients.kernel.command.client.UpdateClientCommand;
import com.devsu.clients.adapter.in.api.client.v1.dto.ClientResponse;
import com.devsu.clients.adapter.in.api.client.v1.dto.CreateClientRequest;
import com.devsu.clients.adapter.in.api.client.v1.dto.UpdateClientRequest;
import com.devsu.clients.kernel.model.Client;
import com.devsu.clients.kernel.model.Page;

public class ClientMapper {
    public static ClientResponse toResponse(final Client client) {
        return new ClientResponse(client.getClientId(), client.getStatus(), client.getName(),
                client.getLastName(), client.getGender(), client.getBirthDate(), client.getDocumentType(),
                client.getDocumentNumber(), client.getAddress(), client.getPhone());
    }

    public static PageResponse<ClientResponse> toPageResponse(Page<Client> clients) {
        return new PageResponse<>(
                clients.elements().stream().map(ClientMapper::toResponse).toList(),
                clients.page(), clients.size(), clients.totalElements());
    }

    public static CreateClientCommand toCreateClientCommand(final CreateClientRequest request) {
        return new CreateClientCommand(request.password(), request.name(), request.lastName(), request.gender(),
                request.birthDate(), request.documentType(), request.documentNumber(), request.address(),
                request.phone());
    }

    public static UpdateClientCommand toUpdateClientCommand(String clientId, final UpdateClientRequest request) {
        return new UpdateClientCommand(clientId, request.password(), request.name(), request.lastName(),
                request.gender(), request.address(), request.phone());
    }
}
