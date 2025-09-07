package com.danimo.hotel.category.application.outputports.persistence;

import com.danimo.hotel.category.domain.Category;

import java.util.List;

public interface FindingAllCategoriesOutPort {
    List<Category> findAllCategories();
}
