package com.negra.projetapirest.mapper;

import com.negra.projetapirest.dto.CategoryFullDto;
import com.negra.projetapirest.model.Category;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryFullDtoMapper {

    CategoryFullDto categoryToCategoryFullDto(Category category);
    Category categoryFullDtoToCategory(CategoryFullDto categoryFullDto);

    List<CategoryFullDto> categoryToCategoryFullDto(Collection<Category> categoryCollection);

}
