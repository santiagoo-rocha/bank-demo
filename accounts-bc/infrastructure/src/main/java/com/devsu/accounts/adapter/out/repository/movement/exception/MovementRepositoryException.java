package com.devsu.accounts.adapter.out.repository.movement.exception;

import com.devsu.accounts.kernel.exception.BusinessException;

public class MovementRepositoryException extends BusinessException{
    public MovementRepositoryException(MovementRepositoryErrorStatus errorStatus) {
        super(errorStatus.name(), errorStatus.getMessage());
    }

    public static MovementRepositoryException notFoundError() {
        return new MovementRepositoryException(MovementRepositoryErrorStatus.ACC_301);
    }

    public static MovementRepositoryException errorSavingMovement() {
        return new MovementRepositoryException(MovementRepositoryErrorStatus.ACC_302);
    }

    public static MovementRepositoryException errorMovementById() {
        return new MovementRepositoryException(MovementRepositoryErrorStatus.ACC_303);
    }

    public static MovementRepositoryException errorGettingMovements() {
        return new MovementRepositoryException(MovementRepositoryErrorStatus.ACC_304);
    }
}
