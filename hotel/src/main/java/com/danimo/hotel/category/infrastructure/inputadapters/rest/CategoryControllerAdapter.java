package com.danimo.hotel.category.infrastructure.inputadapters.rest;

import com.danimo.hotel.category.application.inputports.CreatingCategoryInputPort;
import com.danimo.hotel.category.application.inputports.FindingCategoryByNameInputPort;
import com.danimo.hotel.category.application.inputports.ListingAllCategoriesInputPort;
import com.danimo.hotel.category.application.usecases.createcategory.CreateCategoryDto;
import com.danimo.hotel.category.domain.Category;
import com.danimo.hotel.category.infrastructure.inputadapters.rest.dto.CategoryRequestDto;
import com.danimo.hotel.category.infrastructure.inputadapters.rest.dto.CategoryResponseDto;
import com.danimo.hotel.common.infrastructure.annotations.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categories", description = "Operaciones relacionadas a las categorias de las habitaciones")
@RestController
@RequestMapping("/v1/category")
@WebAdapter
public class CategoryControllerAdapter {
    private final CreatingCategoryInputPort creatingCategoryInputPort;
    private final FindingCategoryByNameInputPort findingCategoryByNameInputPort;
    private final ListingAllCategoriesInputPort listingAllCategoriesInputPort;

    @Autowired
    public CategoryControllerAdapter(CreatingCategoryInputPort creatingCategoryInputPort,
                                     FindingCategoryByNameInputPort findingCategoryByNameInputPort,
                                     ListingAllCategoriesInputPort listingAllCategoriesInputPort) {
        this.creatingCategoryInputPort = creatingCategoryInputPort;
        this.findingCategoryByNameInputPort = findingCategoryByNameInputPort;
        this.listingAllCategoriesInputPort = listingAllCategoriesInputPort;
    }

    @Operation(
            summary = "Registrar nueva categoria",
            description = "Devuelve la información de la categoria correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria creado"),
            @ApiResponse(responseCode = "404", description = "Categoria no creado")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<CategoryResponseDto> createLocation(@RequestBody CategoryRequestDto dto) {
        CreateCategoryDto objectAdaptedFromRestToDomain = dto.toDomain();

        Category location = creatingCategoryInputPort.create(objectAdaptedFromRestToDomain);

        CategoryResponseDto response = CategoryResponseDto.fromDomain(location);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Obtener las categorias existentes",
            description = "Devuelve la información de las categorias correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorias encontradas"),
            @ApiResponse(responseCode = "404", description = "Categorias no encontradas")
    })
    @GetMapping
    @Transactional
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<CategoryResponseDto> categories = listingAllCategoriesInputPort.getAllCategories()
                .stream()
                .map(CategoryResponseDto::fromDomain)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @Operation(
            summary = "Obtener la categoria por nombre",
            description = "Devuelve la información de la categoria correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorias encontrada"),
            @ApiResponse(responseCode = "404", description = "Categorias no encontrada")
    })
    @GetMapping("/{name}")
    @Transactional
    public ResponseEntity<CategoryResponseDto> getCateogryByName(@PathVariable String id) {
        Category category = findingCategoryByNameInputPort.findByName(id);

        return ResponseEntity.ok(CategoryResponseDto.fromDomain(category));
    }
}
