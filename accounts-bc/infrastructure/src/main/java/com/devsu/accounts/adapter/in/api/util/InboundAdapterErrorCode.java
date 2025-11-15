package com.devsu.accounts.adapter.in.api.util;

import com.devsu.accounts.adapter.out.repository.account.exception.AccountRepositoryErrorStatus;
import org.springframework.http.HttpStatus;

import java.util.List;

public enum InboundAdapterErrorCode {
    NOT_FOUND(List.of(AccountRepositoryErrorStatus.ACC_201.name()), HttpStatus.NOT_FOUND);

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
