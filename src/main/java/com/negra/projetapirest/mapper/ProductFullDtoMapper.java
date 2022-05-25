package com.negra.projetapirest.mapper;

import com.negra.projetapirest.dto.ProductFullDto;
import com.negra.projetapirest.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductFullDtoMapper {

    ProductFullDto productToProductFullDto(Product product);

}
