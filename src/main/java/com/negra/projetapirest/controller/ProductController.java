package com.negra.projetapirest.controller;

import com.negra.projetapirest.dto.ProductDto;
import com.negra.projetapirest.dto.ProductFullDto;
import com.negra.projetapirest.dto.ProductWithIdDto;
import com.negra.projetapirest.exception.DataNotFoundException;
import com.negra.projetapirest.exception.DataNotValidException;
import com.negra.projetapirest.exception.DataProcessingException;
import com.negra.projetapirest.service.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private static final String DeleteSuccessMessage = "La suppression du produit est effectuée avec success";

    private IProductService productService;

    @PostMapping
    public ResponseEntity<ProductFullDto> save(@Valid @RequestBody ProductDto productDto, Errors errors) {

        ProductFullDto productFullDto = null;

        if (errors.hasErrors()){
            String errorMessage = errors.getFieldError().getDefaultMessage();

            LOGGER.error(errorMessage);

            return ResponseEntity.badRequest().body(productFullDto);
        }

        try {
            productFullDto = productService.save(productDto);

            LOGGER.info("Product creation ==> id : " + productFullDto.getId() + " , code : " + productFullDto.getCode() + " , libelle : " + productFullDto.getLibelle() + " , idCategory : " + productFullDto.getCategory().getId() + " , categoryLibelle : " + productFullDto.getCategory().getLibelle());

            return ResponseEntity.ok(productFullDto);

        }catch (DataProcessingException e){

            LOGGER.error(e.getMessage());

            return ResponseEntity.internalServerError().body(productFullDto);

        }catch (DataNotValidException | DataNotFoundException e){

            LOGGER.error(e.getMessage());

            return ResponseEntity.badRequest().body(productFullDto);

        }

    }

    @PutMapping
    public ResponseEntity<ProductFullDto> update(@Valid @RequestBody ProductWithIdDto productWithIdDto, Errors errors) {

        ProductFullDto productFullDto = null;

        if (errors.hasErrors()){

            String errorMessage = errors.getFieldError().getDefaultMessage();

            LOGGER.error(errorMessage);

            return ResponseEntity.badRequest().body(productFullDto);
        }

        try {
            productFullDto = productService.update(productWithIdDto);

            LOGGER.info("Product update ==> id : " + productFullDto.getId() + " , code : " + productFullDto.getCode() + " , libelle : " + productFullDto.getLibelle() + " , idCategory : " + productFullDto.getCategory().getId() + " , categoryLibelle : " + productFullDto.getCategory().getLibelle());

            return ResponseEntity.ok(productFullDto);

        }catch (DataProcessingException e){

            LOGGER.error(e.getMessage());

            return ResponseEntity.internalServerError().body(productFullDto);

        }catch (DataNotValidException e){

            LOGGER.error(e.getMessage());

            return ResponseEntity.badRequest().body(productFullDto);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestBody ProductDto productDto) {

        try{
            productService.delete(id);

            LOGGER.info("Product deletion ==> id: " + id + ", libelle: " + productDto.getLibelle());

            return ResponseEntity.ok(DeleteSuccessMessage);

        }catch (DataProcessingException e){

            LOGGER.error(e.getMessage());

            return ResponseEntity.internalServerError().body(e.getMessage());

        }catch (DataNotFoundException e){

            LOGGER.error(e.getMessage());

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ResponseEntity<List<ProductFullDto>> getProducts(@RequestBody Long[] ids) {

        List<ProductFullDto> productFullDtoList = new ArrayList<>();

        try{
            productFullDtoList = productService.getProductsByIds(ids);

            String identifiants = "";
            for (Long id: ids)
                identifiants += id + " ";

            LOGGER.info("Récuperation des produits ==> ids : " + identifiants);

            return new ResponseEntity<>(productFullDtoList, HttpStatus.OK);

        }catch (DataProcessingException e){

            LOGGER.error(e.getMessage());

            return new ResponseEntity<>(productFullDtoList, HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (DataNotFoundException e){

            LOGGER.error(e.getMessage());

            return new ResponseEntity<>(productFullDtoList, HttpStatus.NO_CONTENT);

        }

    }

}
