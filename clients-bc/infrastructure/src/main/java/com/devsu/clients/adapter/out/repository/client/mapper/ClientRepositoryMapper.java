package com.devsu.clients.adapter.out.repository.client.mapper;

import com.devsu.clients.adapter.out.repository.client.dto.ClientEntity;
import com.devsu.clients.kernel.model.Client;

public class ClientRepositoryMapper {

    public static Client toClient(ClientEntity entity){
        return Client.builder()
                .clientId(entity.getId())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .birthDate(entity.getBirthDate())
                .documentType(entity.getDocumentType())
                .documentNumber(entity.getDocumentNumber())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .build();
    }

    public static ClientEntity toEntity(Client client){
        ClientEntity entity = new ClientEntity();
        entity.setId(client.getClientId());
        entity.setPassword(client.getPassword());
        entity.setStatus(client.getStatus());
        entity.setName(client.getName());
        entity.setLastName(client.getLastName());
        entity.setGender(client.getGender());
        entity.setBirthDate(client.getBirthDate());
        entity.setDocumentType(client.getDocumentType());
        entity.setDocumentNumber(client.getDocumentNumber());
        entity.setAddress(client.getAddress());
        entity.setPhone(client.getPhone());

        return entity;
    }
}
