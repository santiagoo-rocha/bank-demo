package com.devsu.accounts.port.repository.movement;


import com.devsu.accounts.kernel.model.Movement;
import com.devsu.accounts.kernel.model.Page;
import com.devsu.accounts.port.repository.movement.dto.GetMovementByAccountRequest;
import reactor.core.publisher.Mono;

public interface MovementRepositoryPort {
    Mono<Page<Movement>> getMovementsByAccount(GetMovementByAccountRequest request);
    Mono<Movement> getMovementById(String movementId);
    Mono<Movement> saveMovement(Movement movement);
}
