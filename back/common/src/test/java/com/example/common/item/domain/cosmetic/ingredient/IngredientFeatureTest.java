package com.example.common.item.domain.cosmetic.ingredient;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class IngredientFeatureTest {

    @Autowired
    IngredientFeatureRepository ingredientFeatureRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    FeatureRepository featureRepository;

    @Autowired
    TestEntityManager em;

    @DisplayName("성분-특징 연관관계를 저장한다")
    @Test
    void saveAndFindIngredientFeature() throws Exception {
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
        IngredientFeature findIF= ingredientFeatureRepository.findAll().get(0);
        assertThat(findIF.getIngredient().getName()).isEqualTo(ingredient.getName());
        assertThat(findIF.getFeature().getName()).isEqualTo(feature.getName());
        assertThat(findIF.getFeature().getStatus()).isEqualTo(feature.getStatus());

        Ingredient findIngredient = ingredientRepository.findAll().get(0);
        assertThat(findIngredient.getName()).isEqualTo(ingredient.getName());

        Feature findFeature = featureRepository.findAll().get(0);
        assertThat(findFeature.getName()).isEqualTo(feature.getName());
        assertThat(findFeature.getStatus()).isEqualTo(feature.getStatus());

    }

    @DisplayName("성분 삭제 시 특징은 삭제되지 않는다")
    @Test
    void deleteCascadeIngredient() throws Exception {
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

        //when
        Ingredient findIngredient = ingredientRepository.findAll().get(0);
        ingredientRepository.deleteById(findIngredient.getId());

        //then
        //삭제 전
        assertThat(findIngredient.getName()).isEqualTo(ingredient.getName());
        //삭제 후
        assertThat(ingredientRepository.count()).isEqualTo(0);
        assertThat(ingredientFeatureRepository.count()).isEqualTo(0);
        assertThat(featureRepository.count()).isEqualTo(1);
    }

}