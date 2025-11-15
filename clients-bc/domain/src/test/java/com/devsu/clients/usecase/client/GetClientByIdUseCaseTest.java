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
import static com.devsu.clients.util.Constants.ADDRESS;
import static com.devsu.clients.util.Samples.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetClientByIdUseCaseTest {

    @Mock
    private ClientRepositoryPort clientRepositoryPort;

    @InjectMocks
    private GetClientByIdUseCase getClientByIdUseCase;

    @Test
    public void shouldGetBookById(){
        when(clientRepositoryPort.getClientById(any())).thenReturn(Mono.just(clientSample()));

        StepVerifier.create(getClientByIdUseCase.execute(getClientByIdQuerySample()))
                .assertNext(it -> {
                    Assertions.assertEquals(CLIENT_ID, it.getClientId());
                    Assertions.assertEquals(NAME, it.getName());
                    Assertions.assertEquals(LAST_NAME, it.getLastName());
                    Assertions.assertEquals(PASSWORD, it.getPassword());
                    Assertions.assertEquals(Gender.M, it.getGender());
                    Assertions.assertEquals(ClientStatus.ENABLE, it.getStatus());
                    Assertions.assertNotNull(it.getBirthDate());
                    Assertions.assertEquals(DocumentType.CC, it.getDocumentType());
                    Assertions.assertEquals(DOCUMENT_NUMBER, it.getDocumentNumber());
                    Assertions.assertEquals(PHONE, it.getPhone());
                    Assertions.assertEquals(ADDRESS, it.getAddress());
                })
                .verifyComplete();
    }
}
