package com.devsu.clients.adapter.in.api;

import com.devsu.clients.kernel.exception.BusinessException;
import com.devsu.clients.adapter.in.api.dto.ErrorResponse;
import com.devsu.clients.adapter.in.api.util.InboundAdapterErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleBusinessException(final BusinessException exception) {
        return Mono.just(getHttpStatus(exception.getCode()))
                .map(status -> ResponseEntity.status(status)
                        .body(new ErrorResponse(exception.getCode(), exception.getMessage())));
    }

    private HttpStatus getHttpStatus(String code) {
        return Arrays.stream(InboundAdapterErrorCode.values())
                .filter(it -> it.getCodes().contains(code))
                .map(InboundAdapterErrorCode::getHttpStatus)
                .findFirst().orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
