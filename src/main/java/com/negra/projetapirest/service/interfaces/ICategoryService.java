package com.negra.projetapirest.service.interfaces;

import com.negra.projetapirest.dto.CategoryDto;
import com.negra.projetapirest.dto.CategoryFullDto;
import com.negra.projetapirest.exception.DataNotFoundException;
import com.negra.projetapirest.exception.DataProcessingException;
import com.negra.projetapirest.model.Category;

import java.util.List;

public interface ICategoryService {

    CategoryFullDto save(CategoryDto categoryDto) throws Exception;

    Category findById(Long id) throws DataProcessingException, DataNotFoundException;

    void update(CategoryFullDto categoryFullDto) throws DataProcessingException, DataNotFoundException;

    void delete(Long id) throws DataProcessingException, DataNotFoundException;

    List<CategoryFullDto> getCategoriesByIds(Long[] ids) throws DataProcessingException, DataNotFoundException;
}
