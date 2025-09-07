package com.danimo.hotel.category.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CategoryCreatedAt {
    private final LocalDateTime createdAt;

    public CategoryCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static CategoryCreatedAt generate() {
        return new CategoryCreatedAt(LocalDateTime.now());
    }

    public static CategoryCreatedAt fromDomain(LocalDateTime createdAt) {
        return new CategoryCreatedAt(createdAt);
    }
}
