package com.example.common.item.domain.cosmetic.ingredient;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class IngredientTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    FeatureRepository featureRepository;

    @Autowired
    IngredientFeatureRepository ingredientFeatureRepository;

    @DisplayName("성분을 저장한다")
    @Test
    void saveAndFindIngredient() throws Exception {
        //given
        Ingredient ingredient = Ingredient.builder()
                .name("쉐어버터")
                .build();
        ingredientRepository.save(ingredient);

        //when & then
        Ingredient findIngredient = ingredientRepository.findByName("쉐어버터");
        assertThat(findIngredient.getName()).isEqualTo(ingredient.getName());
    }

    @DisplayName("성분을 조회한다")
    @Test
    void findIngredientTest() throws Exception {
        //given
        Feature feature = Feature.builder()
                .name("여드름")
                .status(Feature.Status.Warning)
                .build();
        featureRepository.save(feature);
        IngredientFeature ingredientFeature = IngredientFeature.builder()
                .feature(feature)
                .build();
        Ingredient ingredient = Ingredient.builder()
                .name("AA")
                .build();
        ingredient.addIngredientFeature(ingredientFeature);
        ingredientRepository.save(ingredient);

        //when & then
        Ingredient findIngredient= ingredientRepository.findAll().get(0);
        assertThat(findIngredient.getIngredientFeatures().get(0).getFeature().getName()).isEqualTo(feature.getName());
    }


}