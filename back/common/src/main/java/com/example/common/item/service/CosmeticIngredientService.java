package com.example.common.item.service;

import com.example.common.item.domain.cosmetic.CosmeticIngredient;
import com.example.common.item.domain.cosmetic.CosmeticIngredientRepository;
import com.example.common.item.domain.cosmetic.ingredient.Ingredient;
import com.example.common.item.domain.vo.CosmeticIngredientVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CosmeticIngredientService implements ItemService<List<CosmeticIngredient>, CosmeticIngredientVO>{

    private final CosmeticIngredientRepository cosmeticIngredientRepository;

    @Override
    public List<CosmeticIngredient> save(CosmeticIngredientVO cosmeticIngredientVO) {
        List<Ingredient> ingredients = cosmeticIngredientVO.getIngredients();
        List<CosmeticIngredient> cosmeticIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            cosmeticIngredients.add(CosmeticIngredient.builder()
                    .ingredient(ingredient)
                    .build());
        }
        return cosmeticIngredients;
    }
}
