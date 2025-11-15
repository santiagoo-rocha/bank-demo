package com.devsu.accounts.adapter.in.api.dto;

import java.util.List;

public record PageResponse <T>(
        List<T> elements,
        int page,
        int size,
        long totalElements
) {}
