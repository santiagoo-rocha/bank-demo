package com.devsu.clients.kernel.model;

import java.util.List;

public record Page <T>(
        List<T> elements,
        int page,
        int size,
        long totalElements
) {

    public static <T> Builder<T> builder(){
        return new Builder<>();
    }

    public static final class Builder <T> {
        private List<T> elements;
        private int page;
        private int size;
        private long totalElements;

        public Builder<T> elements(List<T> elements) {
            this.elements = elements;
            return this;
        }

        public Builder<T> page(int page) {
            this.page = page;
            return this;
        }

        public Builder<T> size(int size) {
            this.size = size;
            return this;
        }

        public Builder<T> totalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Page<T> build() {
            return new Page<>(elements, page, size, totalElements);
        }
    }
}
