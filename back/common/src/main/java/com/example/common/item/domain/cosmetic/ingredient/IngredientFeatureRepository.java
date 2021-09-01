package com.example.common.item.domain.cosmetic.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientFeatureRepository extends JpaRepository<IngredientFeature,Long> {

    List<IngredientFeature> findAllByIngredient(Ingredient ingredient);
}
