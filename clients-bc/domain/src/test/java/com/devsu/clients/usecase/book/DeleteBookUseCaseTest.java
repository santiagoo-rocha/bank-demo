package com.devsu.clients.usecase.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.devsu.clients.util.Constants.BOOK_ID;
import static com.devsu.clients.util.Samples.deleteBookCommandSample;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteBookUseCaseTest {

    @Mock
    private BookRepositoryPort bookRepositoryPort;

    @InjectMocks
    private DeleteBookUseCase deleteBookUseCase;

    @Test
    public void shouldDeleteBook() {
        when(bookRepositoryPort.deleteBook(any())).thenReturn(Mono.empty());

        StepVerifier.create(deleteBookUseCase.execute(deleteBookCommandSample()))
                .verifyComplete();

        verify(bookRepositoryPort).deleteBook(BOOK_ID);
    }
}
