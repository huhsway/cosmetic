package com.example.common.item.service;

import com.example.common.item.api.dto.IngredientResponseDto;
import com.example.common.item.domain.cosmetic.ingredient.*;
import com.example.common.item.domain.vo.IngredientVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService implements ItemService<List<Ingredient>, IngredientVO>{

    private final IngredientRepository ingredientRepository;
    private final FeatureRepository featureRepository;

    @Transactional
    @Override
    public List<Ingredient> save(IngredientVO ingredientVO){
        Map<Ingredient, List<Feature>> ingredientListMap = ingredientVO.getIngredientListMap();
        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientListMap.keySet()) {
            ingredient = saveIngredientFeature(ingredient, ingredientListMap.get(ingredient));
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    private Ingredient saveIngredientFeature(Ingredient ingredient, List<Feature> featureList) {
        Ingredient findByNameIngredient = ingredientRepository.findByName(ingredient.getName());

        if (findByNameIngredient != null) {
            return findByNameIngredient;
        }

        for (Feature feature : featureList){
            Feature findByNameFeature = featureRepository.findByName(feature.getName());
            Feature saveFeature = findByNameFeature;
            if (findByNameFeature == null){
                saveFeature = featureRepository.save(feature);
            }
            IngredientFeature ingredientFeature = IngredientFeature.builder()
                    .feature(saveFeature)
                    .build();
            ingredient.addIngredientFeature(ingredientFeature);
        }
        return ingredientRepository.save(ingredient);
    }

    @Transactional(readOnly = true)
    public List<IngredientResponseDto> findIngredientsWithFeature() {
        return ingredientRepository.findIngredientsWithFeature().stream()
                .map(IngredientResponseDto::from)
                .collect(Collectors.toList());
    }
}
