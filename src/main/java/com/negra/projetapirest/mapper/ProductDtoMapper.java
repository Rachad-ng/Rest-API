package com.negra.projetapirest.mapper;

import com.negra.projetapirest.dto.ProductDto;
import com.negra.projetapirest.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {

    Product productDtoToProduct(ProductDto productDto);

}
