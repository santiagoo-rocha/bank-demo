package com.devsu.clients.usecase.book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.devsu.clients.util.Constants.*;
import static com.devsu.clients.util.Samples.bookSample;
import static com.devsu.clients.util.Samples.getBookByIdQuerySample;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetBookByIdUseCaseTest {

    @Mock
    private BookRepositoryPort bookRepositoryPort;

    @InjectMocks
    private GetBookByIdUseCase getBookByIdUseCase;

    @Test
    public void shouldGetBookById(){
        when(bookRepositoryPort.getBookById(any())).thenReturn(Mono.just(bookSample()));

        StepVerifier.create(getBookByIdUseCase.execute(getBookByIdQuerySample()))
                .assertNext(it -> {
                    Assertions.assertEquals(BOOK_ID, it.bookId());
                    Assertions.assertEquals(TITLE, it.title());
                    Assertions.assertEquals(AUTHOR, it.author());
                    Assertions.assertEquals(YEAR, it.year());
                    Assertions.assertEquals(EDITION, it.edition());
                })
                .verifyComplete();
    }
}
