package com.devsu.accounts.port.repository.movement.dto;

public record GetMovementByAccountRequest(
        String accountId, int page, int size
){
}
