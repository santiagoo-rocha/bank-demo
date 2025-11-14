package com.devsu.clients.adapter.in.api.client.v1;

import com.devsu.clients.adapter.in.api.dto.PageResponse;
import com.devsu.clients.kernel.command.client.DeleteClientCommand;
import com.devsu.clients.kernel.query.GetClientByIdQuery;
import com.devsu.clients.kernel.query.GetClientsQuery;
import com.devsu.clients.adapter.in.api.client.v1.dto.ClientResponse;
import com.devsu.clients.adapter.in.api.client.v1.dto.CreateClientRequest;
import com.devsu.clients.adapter.in.api.client.v1.dto.UpdateClientRequest;
import com.devsu.clients.adapter.in.api.client.v1.mapper.ClientMapper;
import com.devsu.clients.usecase.client.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {

    private final CreateClientUseCase createClientUseCase;
    private final GetClientByIdUseCase getClientByIdUseCase;
    private final GetClientsUseCase getClientsUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;

    public ClientController(CreateClientUseCase createClientUseCase, GetClientByIdUseCase getClientByIdUseCase, GetClientsUseCase getClientsUseCase, UpdateClientUseCase updateClientUseCase, DeleteClientUseCase deleteClientUseCase) {
        this.createClientUseCase = createClientUseCase;
        this.getClientByIdUseCase = getClientByIdUseCase;
        this.getClientsUseCase = getClientsUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
    }


    @GetMapping
    public Mono<ResponseEntity<PageResponse<ClientResponse>>> getBooks(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        return getClientsUseCase.execute(new GetClientsQuery(page, size))
                .map(ClientMapper::toPageResponse)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{clientId}")
    public Mono<ResponseEntity<ClientResponse>> getClientById(@PathVariable String clientId){
        return getClientByIdUseCase.execute(new GetClientByIdQuery(clientId))
                .map(ClientMapper::toResponse)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<ClientResponse>> createClient(
            @RequestBody @Valid CreateClientRequest createClientRequest
    ){
        return Mono.just(ClientMapper.toCreateClientCommand(createClientRequest))
                .flatMap(createClientUseCase::execute)
                .map(ClientMapper::toResponse)
                .map(it -> new ResponseEntity<>(it, HttpStatus.CREATED));
    }

    @PatchMapping("/{clientId}")
    public Mono<ResponseEntity<ClientResponse>> updateClient(
            @PathVariable String clientId,
            @RequestBody UpdateClientRequest updateClientRequest
    ){
        return Mono.just(ClientMapper.toUpdateClientCommand(clientId, updateClientRequest))
                .flatMap(updateClientUseCase::execute)
                .map(ClientMapper::toResponse)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{clientId}")
    public Mono<ResponseEntity<ClientResponse>> deleteClientById(@PathVariable String clientId){
        return deleteClientUseCase.execute(new DeleteClientCommand(clientId))
                .thenReturn(ResponseEntity.noContent().build());
    }
}
