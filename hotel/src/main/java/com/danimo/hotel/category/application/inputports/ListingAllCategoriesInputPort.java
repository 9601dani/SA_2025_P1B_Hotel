package com.danimo.hotel.category.application.inputports;

import com.danimo.hotel.category.domain.Category;

import java.util.List;

public interface ListingAllCategoriesInputPort {
    List<Category> getAllCategories();
}
