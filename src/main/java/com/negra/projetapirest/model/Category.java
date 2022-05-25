package com.negra.projetapirest.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Vous devez saisir le libelle du categorie")
    @NotEmpty(message = "Vous devez saisir le libelle du categorie")
    @NotBlank(message = "Vous devez saisir le libelle du categorie")
    @Column(nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "category")
    private Set<Product> productSet = new HashSet<>();
}
