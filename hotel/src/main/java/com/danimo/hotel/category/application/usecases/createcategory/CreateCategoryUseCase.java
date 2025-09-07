package com.danimo.hotel.category.application.usecases.createcategory;

import com.danimo.hotel.category.application.inputports.CreatingCategoryInputPort;
import com.danimo.hotel.category.application.outputports.persistence.FindingCategoryByNameOutputPort;
import com.danimo.hotel.category.application.outputports.persistence.StoringCategoryOutputPort;
import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.common.application.annotations.UseCase;
import com.danimo.hotel.common.application.exceptions.EntityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class CreateCategoryUseCase implements CreatingCategoryInputPort {
    private final FindingCategoryByNameOutputPort findingCategoryByNameOutputPort;
    private final StoringCategoryOutputPort storingCategoryOutputPort;
    @Autowired
    public CreateCategoryUseCase(FindingCategoryByNameOutputPort findingCategoryByNameOutputPort,
                                 StoringCategoryOutputPort storingCategoryOutputPort) {
        this.findingCategoryByNameOutputPort = findingCategoryByNameOutputPort;
        this.storingCategoryOutputPort = storingCategoryOutputPort;
    }

    @Override
    public Category create(CreateCategoryDto dto) throws EntityAlreadyExistException {
        if(findingCategoryByNameOutputPort.findByName(dto.getName().toUpperCase()).isPresent()) {
            throw new EntityAlreadyExistException(dto.getName());
        }

        Category newCategory = dto.toDomain();

        return storingCategoryOutputPort.save(newCategory);
    }
}
