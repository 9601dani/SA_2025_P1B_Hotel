package com.danimo.hotel.category.application.outputports.persistence;

import com.danimo.hotel.category.domain.Category;

public interface StoringCategoryOutputPort {
    Category save(Category category);
}
