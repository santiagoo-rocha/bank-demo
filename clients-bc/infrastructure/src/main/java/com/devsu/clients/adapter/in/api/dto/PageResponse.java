package com.devsu.clients.adapter.in.api.dto;

import com.devsu.clients.kernel.model.Page;

import java.util.List;

public record PageResponse <T>(
        List<T> elements,
        int page,
        int size,
        long totalElements
) {}
