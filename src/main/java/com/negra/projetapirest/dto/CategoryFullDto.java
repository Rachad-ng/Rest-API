package com.negra.projetapirest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CategoryFullDto implements Serializable {

    private Long id;

    @NotNull(message = "Vous devez saisir le libelle du categorie")
    @NotEmpty(message = "Vous devez saisir le libelle du categorie")
    @NotBlank(message = "Vous devez saisir le libelle du categorie")
    private String libelle;
}
