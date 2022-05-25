package com.negra.projetapirest.controller;

import com.negra.projetapirest.dto.CategoryDto;
import com.negra.projetapirest.dto.CategoryFullDto;
import com.negra.projetapirest.exception.DataNotFoundException;
import com.negra.projetapirest.exception.DataProcessingException;
import com.negra.projetapirest.service.interfaces.ICategoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private ICategoryService categoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    private static final String DELETION_SUCCESS = "La suppression du categorie est efféctué avec success";

    @PostMapping
    public ResponseEntity<CategoryFullDto> add(@Valid @RequestBody CategoryDto categoryDto, Errors errors) {

        String template = "Category creation ==> id : %s , libelle : %s";

        CategoryFullDto categoryFullDto = null;

        if(errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();

            LOGGER.error(errorMessage);

            return ResponseEntity.badRequest().body(categoryFullDto);
        }

        try{
            categoryFullDto = categoryService.save(categoryDto);

            LOGGER.info(String.format(template, categoryFullDto.getId(), categoryFullDto.getLibelle()));

            return ResponseEntity.ok(categoryFullDto);

        }catch (Exception e){

            LOGGER.error(e.getMessage());

            return ResponseEntity.internalServerError().body(categoryFullDto);
        }

    }

    @PutMapping
    public ResponseEntity<CategoryFullDto> update(@Valid @RequestBody CategoryFullDto categoryFullDto, Errors errors) {

        if(errors.hasErrors()) {
            LOGGER.error(errors.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(categoryFullDto);
        }

        try {
            categoryService.update(categoryFullDto);

            LOGGER.info("Category update ==> id :" + categoryFullDto.getId() + " , libelle : " + categoryFullDto.getLibelle());

            return ResponseEntity.ok(categoryFullDto);

        }catch (DataProcessingException e){

            LOGGER.error(e.getMessage());
            return ResponseEntity.internalServerError().body(categoryFullDto);

        }catch (DataNotFoundException e){

            LOGGER.error(e.getMessage());
            return ResponseEntity.badRequest().body(categoryFullDto);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestBody CategoryFullDto categoryFullDto) {

        try{
            categoryService.delete(id);

            LOGGER.info("Category deletion ==> id: " + id + ", libelle: " + categoryFullDto.getLibelle());

            return ResponseEntity.ok(DELETION_SUCCESS);

        }catch (DataProcessingException e){

            LOGGER.error(e.getMessage());

            return ResponseEntity.internalServerError().body(e.getMessage());

        }catch (DataNotFoundException e){

            LOGGER.error(e.getMessage());

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<List<CategoryFullDto>> getCategories(@RequestBody Long[] ids) {

        List<CategoryFullDto> categoryFullDtoList = new ArrayList<>();

        try{
            categoryFullDtoList = categoryService.getCategoriesByIds(ids);

            String identifiants = "";
            for (Long id: ids)
                identifiants += id + " ";

            LOGGER.info("Récuperation des categories ==> ids : " + identifiants);

            return ResponseEntity.ok(categoryFullDtoList);

        }catch (DataProcessingException e){

            LOGGER.error(e.getMessage());

            return ResponseEntity.internalServerError().body(categoryFullDtoList);

        }catch (DataNotFoundException e){

            LOGGER.error(e.getMessage());

            return ResponseEntity.badRequest().body(categoryFullDtoList);

        }

    }

}
