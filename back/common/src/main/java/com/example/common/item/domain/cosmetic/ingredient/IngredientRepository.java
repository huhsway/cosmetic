package com.example.common.item.domain.cosmetic.ingredient;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("select distinct i from Ingredient i" +
            " join fetch i.ingredientFeatures if" +
            " join fetch if.feature f")
    List<Ingredient> findIngredientsWithFeature();
    Ingredient findByName(String name);
}
