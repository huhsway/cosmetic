package com.example.common.item.api.dto;

import com.example.common.item.domain.cosmetic.ingredient.Ingredient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class IngredientResponseDto {
    private Long id;
    private String name;
    private List<FeatureResponseDto> features;

    private IngredientResponseDto(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.features = ingredient.getIngredientFeatures()
                .stream()
                .map(ingredientFeature -> FeatureResponseDto.from(ingredientFeature.getFeature()))
                .collect(Collectors.toList());
    }

    public static IngredientResponseDto from(Ingredient ingredient) {
        return new IngredientResponseDto(ingredient);
    }
}
