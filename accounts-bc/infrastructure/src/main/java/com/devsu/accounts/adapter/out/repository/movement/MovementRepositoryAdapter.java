package com.devsu.accounts.adapter.out.repository.movement;

import com.devsu.accounts.adapter.out.repository.account.dto.AccountEntity;
import com.devsu.accounts.adapter.out.repository.movement.dto.MovementEntity;
import com.devsu.accounts.adapter.out.repository.movement.exception.MovementRepositoryException;
import com.devsu.accounts.adapter.out.repository.movement.mapper.MovementRepositoryMapper;
import com.devsu.accounts.kernel.model.Movement;
import com.devsu.accounts.kernel.model.Page;
import com.devsu.accounts.port.repository.movement.MovementRepositoryPort;
import com.devsu.accounts.port.repository.movement.dto.GetMovementByAccountRequest;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MovementRepositoryAdapter implements MovementRepositoryPort {
    private final Logger log = LoggerFactory.getLogger(MovementRepositoryAdapter.class);

    private final EntityManager entityManager;
    private final SpringDataMovementRepository movementRepository;

    public MovementRepositoryAdapter(EntityManager entityManager, SpringDataMovementRepository movementRepository) {
        this.entityManager = entityManager;
        this.movementRepository = movementRepository;
    }

    @Override
    public Mono<Page<Movement>> getMovementsByAccount(GetMovementByAccountRequest request) {
        return Mono.fromCallable( () -> movementRepository.findAllByAccountId(request.accountId(),
                PageRequest.of(request.page(), request.size())))
                .subscribeOn(Schedulers.boundedElastic())
                .map(it -> Page.<Movement>builder()
                        .elements(it.map(MovementRepositoryMapper::toMovement).toList())
                        .page(request.page())
                        .size(request.size())
                        .totalElements(it.getTotalElements())
                        .build()
                )
                .doOnError(ex -> log.error("Error getting movements, err {}", ex.getMessage()))
                .onErrorMap(_ -> MovementRepositoryException.errorGettingMovements());

    }

    @Override
    public Mono<Movement> getMovementById(String movementId) {
        return Mono.fromCallable(() -> movementRepository.findById(movementId))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(Mono::justOrEmpty)
                .map(MovementRepositoryMapper::toMovement)
                .doOnError(it -> log.error("Error getting movement with id {} err {}", movementId, it.getMessage()))
                .onErrorMap(_ -> MovementRepositoryException.errorMovementById())
                .switchIfEmpty(Mono.error(MovementRepositoryException.notFoundError()));
    }

    @Override
    public Mono<Movement> saveMovement(Movement movement) {
        return Mono.just(movement)
                .map(MovementRepositoryMapper::toEntity)
                .map(this::setReference)
                .flatMap(it -> Mono.fromCallable(() -> movementRepository.save(it))
                        .subscribeOn(Schedulers.boundedElastic()))
                .map(MovementRepositoryMapper::toMovement)
                .doOnError(it -> log.error("Error saving movement with id {} err {}", movement.accountId(), it.getMessage()))
                .onErrorMap(_ -> MovementRepositoryException.errorSavingMovement());
    }

    private MovementEntity setReference(MovementEntity entity) {
        AccountEntity account = entityManager.getReference(AccountEntity.class, entity.getAccountId());
        entity.setAccount(account);

        return entity;
    }
}
