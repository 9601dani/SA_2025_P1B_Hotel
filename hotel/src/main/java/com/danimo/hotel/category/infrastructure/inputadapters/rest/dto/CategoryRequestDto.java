package com.danimo.hotel.category.infrastructure.inputadapters.rest.dto;

import com.danimo.hotel.category.application.usecases.createcategory.CreateCategoryDto;
import com.danimo.hotel.category.domain.CategoryCreatedAt;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CategoryRequestDto {
    @NotBlank
    private String name;

    public CreateCategoryDto toDomain(){
        return new CreateCategoryDto(
                name,
                CategoryCreatedAt.generate()
        );
    }
}
