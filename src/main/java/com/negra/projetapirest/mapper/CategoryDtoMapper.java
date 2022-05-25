package com.negra.projetapirest.mapper;

import com.negra.projetapirest.dto.CategoryDto;
import com.negra.projetapirest.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

    Category categoryDtoToCategory(CategoryDto categoryDto);

}
