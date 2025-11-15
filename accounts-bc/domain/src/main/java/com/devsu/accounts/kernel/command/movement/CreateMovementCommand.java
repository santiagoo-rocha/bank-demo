package com.devsu.accounts.kernel.command.movement;

import com.devsu.accounts.kernel.model.enums.MovementType;

import java.math.BigDecimal;

public record CreateMovementCommand(
        String accountId,
        BigDecimal value,
        MovementType type
) {

}
