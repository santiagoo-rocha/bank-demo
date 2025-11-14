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
import static com.devsu.clients.util.Constants.EDITION;
import static com.devsu.clients.util.Samples.bookSample;
import static com.devsu.clients.util.Samples.updateBookCommandSample;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateBookUseCaseTest {
    @Mock
    private BookRepositoryPort bookRepositoryPort;

    @InjectMocks
    private UpdateBookUseCase updateBookUseCase;

    @Test
    public void shouldUpdateBook() {
        when(bookRepositoryPort.getBookById(any())).thenReturn(Mono.just(bookSample()));
        when(bookRepositoryPort.saveBook(any()))
                .thenAnswer(it -> Mono.just(it.getArguments()[0]));

        StepVerifier.create(updateBookUseCase.execute(updateBookCommandSample()))
                .assertNext(it -> {
                    Assertions.assertEquals(BOOK_ID,it.bookId());
                    Assertions.assertEquals(TITLE, it.author());
                    Assertions.assertEquals(AUTHOR, it.title());
                    Assertions.assertEquals(YEAR, it.year());
                    Assertions.assertEquals(EDITION, it.edition());
                })
                .verifyComplete();
    }
}
