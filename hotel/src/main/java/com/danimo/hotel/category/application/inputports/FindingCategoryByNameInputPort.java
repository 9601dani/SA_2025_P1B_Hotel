package com.danimo.hotel.category.application.inputports;

import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;

public interface FindingCategoryByNameInputPort {
    Category findByName(String name) throws EntityNotFoundException;
}
