package com.devsu.clients.adapter.in.api.util;

import com.devsu.clients.adapter.out.repository.client.exception.ClientRepositoryErrorStatus;
import org.springframework.http.HttpStatus;

import java.util.List;

public enum InboundAdapterErrorCode {
    NOT_FOUND(List.of(ClientRepositoryErrorStatus.CLI_101.name()), HttpStatus.NOT_FOUND);

    private final List<String> codes;
    private final HttpStatus httpStatus;

    InboundAdapterErrorCode(final List<String> codes, final HttpStatus httpStatus) {
        this.codes = codes;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public List<String> getCodes() {
        return codes;
    }
}
