package com.negra.projetapirest.service.implementations;

import com.negra.projetapirest.dto.CategoryDto;
import com.negra.projetapirest.dto.CategoryFullDto;
import com.negra.projetapirest.exception.DataNotFoundException;
import com.negra.projetapirest.exception.DataProcessingException;
import com.negra.projetapirest.mapper.CategoryDtoMapper;
import com.negra.projetapirest.mapper.CategoryFullDtoMapper;
import com.negra.projetapirest.model.Category;
import com.negra.projetapirest.repository.CategoryRepository;
import com.negra.projetapirest.service.interfaces.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class CategoryService implements ICategoryService {

    private CategoryRepository categoryRepository;

    private CategoryDtoMapper categoryDtoMapper;
    private CategoryFullDtoMapper categoryFullDtoMapper;

    private static final String DATA_SERVER_ERROR = "Le serveur de données est hors service!!";
    private static final String DATA_PROCESSING_FAILED = "Le traitement de données est échoué.!!";
    private static final String CATEGORY_NOT_FOUND = "Aucune categorie trouvé.!!";

    @Override
    public CategoryFullDto save(CategoryDto categoryDto) throws DataProcessingException {

        Category category = categoryDtoMapper.categoryDtoToCategory(categoryDto);
        try {
            return categoryFullDtoMapper.categoryToCategoryFullDto(categoryRepository.save(category));
        }catch (Exception e){
            throw new DataProcessingException(e.getMessage());
        }
    }

    @Override
    public Category findById(Long id) throws DataProcessingException, DataNotFoundException {

        Optional<Category> optionalCategory;
        try{
            optionalCategory = categoryRepository.findById(id);
        }catch (Exception e){
            throw new DataProcessingException(DATA_SERVER_ERROR);
        }

        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            return category;
        }else{
            throw new DataNotFoundException(CATEGORY_NOT_FOUND);
        }
    }

    @Override
    public void update(CategoryFullDto categoryFullDto) throws DataProcessingException, DataNotFoundException {

        Category category = findById(categoryFullDto.getId());

        category.setLibelle(categoryFullDto.getLibelle());

    }

    @Override
    public void delete(Long id) throws DataProcessingException, DataNotFoundException {

        Category category = findById(id);

        try{
            categoryRepository.delete(category);
        }catch (Exception e){
            throw new DataProcessingException(DATA_PROCESSING_FAILED);
        }

    }

    @Override
    public List<CategoryFullDto> getCategoriesByIds(Long[] ids) throws DataProcessingException, DataNotFoundException {

        List<CategoryFullDto> categoryFullDtoList = new ArrayList<>();

        Category category;
        for (Long id: ids) {
            category = findById(id);
            categoryFullDtoList.add(categoryFullDtoMapper.categoryToCategoryFullDto(category));
        }

        return categoryFullDtoList;
    }
}
