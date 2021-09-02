package com.example.common.item.domain.vo;

import com.example.common.item.domain.cosmetic.ingredient.Ingredient;
import lombok.Getter;

import java.util.List;

@Getter
public class CosmeticIngredientVO {
    private final List<Ingredient> ingredients;

    public CosmeticIngredientVO(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
