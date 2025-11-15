package com.devsu.clients.usecase.client;

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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.devsu.clients.util.Constants.*;
import static com.devsu.clients.util.Samples.createClientCommandSample;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateClientUseCaseTest {

    @Mock
    private ClientRepositoryPort clientRepositoryPort;

    @InjectMocks
    private CreateClientUseCase createClientUseCase;

    @Test
    public void shouldCreateClient(){
        when(clientRepositoryPort.saveClient(any()))
                .thenAnswer(it -> Mono.just(it.getArguments()[0]));

        StepVerifier.create(createClientUseCase.execute(createClientCommandSample()))
                .assertNext(it -> {
                    Assertions.assertNotNull(it.getClientId());
                    Assertions.assertEquals(NAME, it.getName());
                    Assertions.assertEquals(LAST_NAME, it.getLastName());
                    Assertions.assertEquals(PASSWORD, it.getPassword());
                    Assertions.assertEquals(Gender.M, it.getGender());
                    Assertions.assertNotNull(it.getBirthDate());
                    Assertions.assertEquals(ClientStatus.ENABLE, it.getStatus());
                    Assertions.assertEquals(DocumentType.CC, it.getDocumentType());
                    Assertions.assertEquals(DOCUMENT_NUMBER, it.getDocumentNumber());
                    Assertions.assertEquals(PHONE, it.getPhone());
                    Assertions.assertEquals(ADDRESS, it.getAddress());
                })
                .verifyComplete();
    }
}
