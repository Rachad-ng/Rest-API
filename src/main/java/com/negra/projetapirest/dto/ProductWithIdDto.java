package com.negra.projetapirest.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
public class ProductWithIdDto implements Serializable {

    private Long id;

    @NotNull(message = "Vous devez saisir le code du produit")
    @NotEmpty(message = "Vous devez saisir le code du produit")
    @NotBlank(message = "Vous devez saisir le code du produit")
    private String code;

    @NotNull(message = "Vous devez saisir le libelle du produit")
    @NotEmpty(message = "Vous devez saisir le libelle du produit")
    @NotBlank(message = "Vous devez saisir le libelle du produit")
    private String libelle;

    @NotNull(message = "Vous devez saisir le prix de produit")
    @Positive(message = "Le prix n'est pas valid")
    private Double price;

    @NotNull(message = "Vous devez saisir le nombre des unités")
    @Positive(message = "Le nombre des unités n'est pas valid")
    private int amount;

    private CategoryIdDto category;
}
