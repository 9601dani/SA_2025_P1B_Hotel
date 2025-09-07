package com.danimo.hotel.category.application.usecases.findcategory;

import com.danimo.hotel.category.application.inputports.FindingCategoryByNameInputPort;
import com.danimo.hotel.category.application.outputports.persistence.FindingCategoryByNameOutputPort;
import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class FindCategoryByNameUseCase implements FindingCategoryByNameInputPort {
    private final FindingCategoryByNameOutputPort findingCategoryByNameOutputPort;

    @Autowired
    FindCategoryByNameUseCase(FindingCategoryByNameOutputPort findingCategoryByNameOutputPort) {
        this.findingCategoryByNameOutputPort = findingCategoryByNameOutputPort;
    }


    @Override
    public Category findByName(String name) throws EntityNotFoundException {
        return findingCategoryByNameOutputPort.findByName(name).orElseThrow(() -> new EntityNotFoundException("La categoria no existe"));
    }
}
