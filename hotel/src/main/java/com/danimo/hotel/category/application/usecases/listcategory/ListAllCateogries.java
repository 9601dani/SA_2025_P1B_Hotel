package com.danimo.hotel.category.application.usecases.listcategory;

import com.danimo.hotel.category.application.inputports.ListingAllCategoriesInputPort;
import com.danimo.hotel.category.application.outputports.persistence.FindingAllCategoriesOutPort;
import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.common.application.annotations.UseCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class ListAllCateogries implements ListingAllCategoriesInputPort {
    private final FindingAllCategoriesOutPort findingAllCategoriesOutPort;

    @Autowired
    public ListAllCateogries(FindingAllCategoriesOutPort findingAllCategoriesOutPort) {
        this.findingAllCategoriesOutPort = findingAllCategoriesOutPort;
    }

    @Override
    public List<Category> getAllCategories() {
        return findingAllCategoriesOutPort.findAllCategories();
    }
}
