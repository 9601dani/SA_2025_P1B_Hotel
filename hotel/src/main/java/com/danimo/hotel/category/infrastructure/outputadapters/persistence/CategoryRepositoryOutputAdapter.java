package com.danimo.hotel.category.infrastructure.outputadapters.persistence;

import com.danimo.hotel.category.application.outputports.persistence.FindingAllCategoriesOutPort;
import com.danimo.hotel.category.application.outputports.persistence.FindingCategoryByNameOutputPort;
import com.danimo.hotel.category.application.outputports.persistence.StoringCategoryOutputPort;
import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.category.infrastructure.outputadapters.persistence.entity.CategoryDbEntity;
import com.danimo.hotel.category.infrastructure.outputadapters.persistence.entity.mapper.CategoryPersistenceMapper;
import com.danimo.hotel.category.infrastructure.outputadapters.persistence.repository.CategoryDbEntityJpaRepository;
import com.danimo.hotel.common.infrastructure.annotations.PersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
public class CategoryRepositoryOutputAdapter implements FindingCategoryByNameOutputPort, FindingAllCategoriesOutPort
, StoringCategoryOutputPort {
    private final CategoryDbEntityJpaRepository categoryDbEntityJpaRepository;
    private final CategoryPersistenceMapper categoryPersistenceMapper;

    @Autowired
    public CategoryRepositoryOutputAdapter(CategoryDbEntityJpaRepository categoryDbEntityJpaRepository, CategoryPersistenceMapper categoryPersistenceMapper) {
        this.categoryDbEntityJpaRepository = categoryDbEntityJpaRepository;
        this.categoryPersistenceMapper = categoryPersistenceMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return categoryDbEntityJpaRepository.findAll()
                .stream()
                .map(categoryPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findByName(String name) {
       return categoryDbEntityJpaRepository.findById(name)
               .map(categoryPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Category save(Category category) {
        CategoryDbEntity savedCategory = categoryDbEntityJpaRepository.save(categoryPersistenceMapper.toDbEntity(category));

        return categoryPersistenceMapper.toDomain(savedCategory);
    }
}
