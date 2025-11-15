package com.devsu.clients.usecase.client;

import com.devsu.clients.port.repository.ClientRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.devsu.clients.util.Constants.CLIENT_ID;
import static com.devsu.clients.util.Samples.deleteClientCommandSample;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteClientUseCaseTest {

    @Mock
    private ClientRepositoryPort clientRepositoryPort;

    @InjectMocks
    private DeleteClientUseCase deleteClientUseCase;

    @Test
    public void shouldDeleteBook() {
        when(clientRepositoryPort.deleteClient(any())).thenReturn(Mono.empty());

        StepVerifier.create(deleteClientUseCase.execute(deleteClientCommandSample()))
                .verifyComplete();

        verify(clientRepositoryPort).deleteClient(CLIENT_ID);
    }
}
