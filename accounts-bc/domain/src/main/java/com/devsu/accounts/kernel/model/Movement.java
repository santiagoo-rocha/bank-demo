package com.devsu.accounts.kernel.model;

import com.devsu.accounts.kernel.model.enums.MovementType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record Movement(
        String movementId,
        String accountId,
        MovementType type,
        OffsetDateTime creationDate,
        BigDecimal value
) {
}
