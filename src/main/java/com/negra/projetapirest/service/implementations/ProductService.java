package com.negra.projetapirest.service.implementations;

import com.negra.projetapirest.dto.ProductDto;
import com.negra.projetapirest.dto.ProductFullDto;
import com.negra.projetapirest.dto.ProductWithIdDto;
import com.negra.projetapirest.exception.DataNotFoundException;
import com.negra.projetapirest.exception.DataNotValidException;
import com.negra.projetapirest.exception.DataProcessingException;
import com.negra.projetapirest.mapper.ProductDtoMapper;
import com.negra.projetapirest.mapper.ProductFullDtoMapper;
import com.negra.projetapirest.model.Category;
import com.negra.projetapirest.model.Product;
import com.negra.projetapirest.repository.ProductRepository;
import com.negra.projetapirest.service.interfaces.ICategoryService;
import com.negra.projetapirest.service.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class ProductService implements IProductService {

    private ProductRepository productRepository;
    private ICategoryService categoryService;
    private ProductDtoMapper productDtoMapper;
    private ProductFullDtoMapper productFullDtoMapper;

    private static final String DATA_SERVER_ERROR = "Le serveur de données est hors service!!";
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Aucun produit trouvé.!!";
    private static final String DATA_NOT_VALID = "Le prix et/ou la quantité doivent étre positives.!!";

    @Override
    public ProductFullDto save(ProductDto productDto) throws DataProcessingException, DataNotFoundException, DataNotValidException {

        if(productDto.getPrice() <= 0 || productDto.getAmount() <= 0)
            throw new DataNotValidException(DATA_NOT_VALID);

        Product product = productDtoMapper.productDtoToProduct(productDto);
        product.setCategory(categoryService.findById(product.getCategory().getId()));
        try {
            product = productRepository.save(product);

            return productFullDtoMapper.productToProductFullDto(product);
        }catch (Exception e){
            throw new DataProcessingException(e.getMessage());
        }

    }

    @Override
    public Product findById(Long id) throws DataProcessingException, DataNotFoundException {

        Optional<Product> optionalProduct;

        try{
            optionalProduct = productRepository.findById(id);
        }catch (Exception e){
            throw new DataProcessingException(DATA_SERVER_ERROR);
        }

        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            return product;
        }else{
            throw new DataNotFoundException(PRODUCT_NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public ProductFullDto update(ProductWithIdDto productWithIdDto) throws DataProcessingException, DataNotValidException {

        if(productWithIdDto.getPrice() <= 0 || productWithIdDto.getAmount() <= 0)
            throw new DataNotValidException(DATA_NOT_VALID);

         try{
             Category category = categoryService.findById(productWithIdDto.getCategory().getId());

             Product product = findById(productWithIdDto.getId());

             product.setCategory(category);
             product.setPrice(productWithIdDto.getPrice());
             product.setLibelle(productWithIdDto.getLibelle());
             product.setCode(productWithIdDto.getCode());

             return productFullDtoMapper.productToProductFullDto(product);

         }catch (Exception e){
            throw new DataProcessingException(e.getMessage());
         }

    }

    @Override
    public void delete(Long id) throws DataProcessingException, DataNotFoundException {

        Product product = findById(id);

        try{
            productRepository.delete(product);
        }catch (Exception e){
            throw new DataProcessingException(e.getMessage());
        }

    }

    @Override
    public List<ProductFullDto> getProductsByIds(Long[] ids) throws DataProcessingException, DataNotFoundException{

        List<ProductFullDto> productFullDtoList = new ArrayList<>();

        Product product;
        for (Long id: ids) {
            product = findById(id);
            productFullDtoList.add(productFullDtoMapper.productToProductFullDto(product));
        }

        return productFullDtoList;

    }


}
