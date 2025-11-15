package com.devsu.clients.usecase.client;

import com.devsu.clients.kernel.model.Page;
import com.devsu.clients.kernel.model.enums.ClientStatus;
import com.devsu.clients.kernel.model.enums.DocumentType;
import com.devsu.clients.kernel.model.enums.Gender;
import com.devsu.clients.port.repository.ClientRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static com.devsu.clients.util.Constants.*;
import static com.devsu.clients.util.Constants.ADDRESS;
import static com.devsu.clients.util.Samples.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetBooksUseCaseTest {

    @Mock
    private ClientRepositoryPort clientRepositoryPort;

    @InjectMocks
    private GetClientsUseCase getClientsUseCase;

    @Test
    public void shouldGetBooks() {
        when(clientRepositoryPort.getAllClients(any()))
                .thenReturn(Mono.just(new Page<>(List.of(clientSample()), 0, 1, 1)));

        StepVerifier.create(getClientsUseCase.execute(getClientsQuerySample()))
                .assertNext(it -> {
                    Assertions.assertEquals(CLIENT_ID, it.elements().getFirst().getClientId());
                    Assertions.assertEquals(NAME, it.elements().getFirst().getName());
                    Assertions.assertEquals(LAST_NAME, it.elements().getFirst().getLastName());
                    Assertions.assertEquals(PASSWORD, it.elements().getFirst().getPassword());
                    Assertions.assertEquals(Gender.M, it.elements().getFirst().getGender());
                    Assertions.assertEquals(ClientStatus.ENABLE, it.elements().getFirst().getStatus());
                    Assertions.assertEquals(DocumentType.CC, it.elements().getFirst().getDocumentType());
                    Assertions.assertEquals(DOCUMENT_NUMBER, it.elements().getFirst().getDocumentNumber());
                    Assertions.assertEquals(PHONE, it.elements().getFirst().getPhone());
                    Assertions.assertEquals(ADDRESS, it.elements().getFirst().getAddress());
                })
                .verifyComplete();
    }
}
