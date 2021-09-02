package com.example.common.item.domain.vo;

import com.example.common.item.domain.cosmetic.Cosmetic;
import com.example.common.item.domain.cosmetic.CosmeticIngredient;
import lombok.Getter;

import java.util.List;

@Getter
public class CosmeticVO {
    private Cosmetic cosmetic;
    private List<CosmeticIngredient> cosmeticIngredients;

    public CosmeticVO(Cosmetic cosmetic, List<CosmeticIngredient> cosmeticIngredients) {
        this.cosmetic = cosmetic;
        this.cosmeticIngredients = cosmeticIngredients;
    }
}
