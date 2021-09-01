package com.example.common.item.domain.cosmetic.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientFeature {

    @Id
    @Column(name = "ingredient_feature_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id")
    private Feature feature;

    @Builder
    public IngredientFeature(Feature feature){
        this.feature = feature;
    }
}
