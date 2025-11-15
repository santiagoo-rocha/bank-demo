package com.devsu.accounts.adapter.out.repository.movement.mapper;

import com.devsu.accounts.adapter.out.repository.movement.dto.MovementEntity;
import com.devsu.accounts.kernel.model.Movement;

public class MovementRepositoryMapper {
    public static Movement toMovement(MovementEntity entity){
        return new Movement(entity.getId(), entity.getAccountId(), entity.getType(), entity.getCreationDate(),
                entity.getValue());
    }

    public static MovementEntity toEntity(Movement movement){
        MovementEntity entity = new MovementEntity();
        entity.setId(movement.movementId());
        entity.setAccountId(movement.accountId());
        entity.setType(movement.type());
        entity.setCreationDate(movement.creationDate());
        entity.setValue(movement.value());

        return entity;
    }
}
