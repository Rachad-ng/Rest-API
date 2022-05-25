package com.negra.projetapirest.repository;

import com.negra.projetapirest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
