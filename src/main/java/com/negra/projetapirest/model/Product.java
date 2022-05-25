package com.negra.projetapirest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Vous devez saisir le code du produit")
    @NotEmpty(message = "Vous devez saisir le code du produit")
    @NotBlank(message = "Vous devez saisir le code du produit")
    @Column(nullable = false)
    private String code;

    @NotNull(message = "Vous devez saisir le libelle du produit")
    @NotEmpty(message = "Vous devez saisir le libelle du produit")
    @NotBlank(message = "Vous devez saisir le libelle du produit")
    @Column(nullable = false)
    private String libelle;

    @NotNull(message = "Vous devez saisir le prix de produit")
    @Positive(message = "Le prix n'est pas valid")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Vous devez saisir le nombre des unités")
    @Positive(message = "Le nombre des unités n'est pas valid")
    @Column(nullable = false)
    private int amount;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
