package com.devsu.clients.adapter.out.repository.client;

import com.devsu.clients.adapter.out.repository.client.dto.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SpringDataClientRepository extends JpaRepository<ClientEntity, String> {
}
