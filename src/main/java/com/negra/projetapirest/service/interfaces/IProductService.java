package com.negra.projetapirest.service.interfaces;

import com.negra.projetapirest.dto.ProductDto;
import com.negra.projetapirest.dto.ProductFullDto;
import com.negra.projetapirest.dto.ProductWithIdDto;
import com.negra.projetapirest.exception.DataNotFoundException;
import com.negra.projetapirest.exception.DataNotValidException;
import com.negra.projetapirest.exception.DataProcessingException;
import com.negra.projetapirest.model.Product;

import java.util.List;

public interface IProductService {

    ProductFullDto save(ProductDto productDto) throws DataProcessingException, DataNotFoundException, DataNotValidException;

    Product findById(Long id) throws DataProcessingException, DataNotFoundException;

    ProductFullDto update(ProductWithIdDto productWithIdDto) throws DataProcessingException, DataNotValidException;

    void delete(Long id) throws DataProcessingException, DataNotFoundException;

    List<ProductFullDto> getProductsByIds(Long[] ids) throws DataProcessingException, DataNotFoundException;
}
