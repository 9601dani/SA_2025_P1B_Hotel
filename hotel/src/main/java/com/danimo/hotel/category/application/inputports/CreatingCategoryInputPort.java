package com.danimo.hotel.category.application.inputports;

import com.danimo.hotel.category.application.usecases.createcategory.CreateCategoryDto;
import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.common.application.exceptions.EntityAlreadyExistException;

public interface CreatingCategoryInputPort {
    Category create(CreateCategoryDto dto) throws EntityAlreadyExistException;
}
