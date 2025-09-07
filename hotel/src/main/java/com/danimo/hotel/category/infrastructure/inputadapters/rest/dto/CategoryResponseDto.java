package com.danimo.hotel.category.infrastructure.inputadapters.rest.dto;

import com.danimo.hotel.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class CategoryResponseDto {
    private String name;

    public static CategoryResponseDto fromDomain(Category category) {
        return new CategoryResponseDto(category.getName());
    }
}
