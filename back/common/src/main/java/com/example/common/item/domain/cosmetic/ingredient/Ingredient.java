package com.example.common.item.domain.cosmetic.ingredient;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<IngredientFeature> ingredientFeatures = new ArrayList<>();

    @Builder
    public Ingredient(String name){
        this.name = name;
    }

    //==연관관계 메서드==//
    public void addIngredientFeature(IngredientFeature ingredientFeature) {
        ingredientFeatures.add(ingredientFeature);
        ingredientFeature.setIngredient(this);
    }
}

