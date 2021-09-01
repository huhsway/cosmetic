package com.example.common.item.domain.cosmetic;

import com.example.common.item.domain.cosmetic.ingredient.Ingredient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CosmeticIngredient {

    @Id
    @Column(name = "cosmetic_ingredient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Cosmetic cosmetic;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Builder
    public CosmeticIngredient(Ingredient ingredient){
        this.ingredient = ingredient;
    }
}
