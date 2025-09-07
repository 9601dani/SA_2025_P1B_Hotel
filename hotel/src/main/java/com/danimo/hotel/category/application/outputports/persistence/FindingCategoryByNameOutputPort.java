package com.danimo.hotel.category.application.outputports.persistence;

import com.danimo.hotel.category.domain.Category;

import java.util.Optional;

public interface FindingCategoryByNameOutputPort {
    Optional<Category> findByName(String name);
}
