package com.danimo.hotel.category.domain;

import com.danimo.hotel.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@DomainEntity
@AllArgsConstructor
public class Category {
    private String name;
    private CategoryCreatedAt createdAt;

    public Category(String name) {
        this.name = name;
    }

    public static Category fromString(String category) {
        return new Category(category);
    }
}
