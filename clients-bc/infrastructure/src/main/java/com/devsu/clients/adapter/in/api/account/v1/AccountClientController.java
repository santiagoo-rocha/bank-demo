package com.devsu.clients.adapter.in.api.account.v1;

import com.devsu.clients.adapter.in.api.account.v1.dto.AccountCreationResponse;
import com.devsu.clients.adapter.in.api.account.v1.dto.CreateAccountRequest;
import com.devsu.clients.adapter.in.api.account.v1.mapper.AccountClientMapper;
import com.devsu.clients.kernel.command.account.CreateAccountCommand;
import com.devsu.clients.usecase.account.CreateAccountUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/clients/{clientId}/accounts")
public class AccountClientController {

    private final CreateAccountUseCase createAccountUseCase;

    public AccountClientController(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    @PostMapping
    public Mono<ResponseEntity<AccountCreationResponse>> createAccount(
            @PathVariable String clientId,
            @RequestBody @Valid CreateAccountRequest createAccountRequest
    ){
        return Mono.just(AccountClientMapper.toCreateAccountCommand(clientId, createAccountRequest))
                .flatMap(createAccountUseCase::execute)
                .map(it -> AccountClientMapper.toAccountCreationResponse(it, clientId, createAccountRequest))
                .map(ResponseEntity::ok);
    }
}
