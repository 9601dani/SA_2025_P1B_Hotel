package com.danimo.hotel.category.application.usecases.createcategory;

import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.category.domain.CategoryCreatedAt;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreateCategoryDto {
    private String name;
    private CategoryCreatedAt categoryCreatedAt;

    public Category toDomain(){
        return new Category(name.toUpperCase(), categoryCreatedAt);
    }
}
