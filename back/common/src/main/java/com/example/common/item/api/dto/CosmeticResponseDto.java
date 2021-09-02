package com.example.common.item.api.dto;

import com.example.common.item.domain.cosmetic.Cosmetic;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CosmeticResponseDto {
    private String skinType;
    private String shape;
    private String useArea;
    private String volume;
    private String mainFeature;
    private String detailFeature;
    //SkinCare
    private String useTime;
    //SunCare
    private String paRating;
    private String sunBlockRating;
    //ColorMakeUp
    private String color;
    private String effect;
    private String type;
    private List<IngredientResponseDto> ingredients;


    private CosmeticResponseDto(Cosmetic cosmetic) {
        this.skinType = cosmetic.getSkinType();
        this.shape = cosmetic.getShape();
        this.useArea = cosmetic.getUseArea();
        this.volume = cosmetic.getVolume();
        this.mainFeature = cosmetic.getMainFeature();
        this.detailFeature = cosmetic.getDetailFeature();
        this.useTime = cosmetic.getUseTime();
        this.paRating = cosmetic.getPaRating();
        this.sunBlockRating = cosmetic.getSunBlockRating();
        this.color = cosmetic.getColor();
        this.effect = cosmetic.getEffect();
        this.type = cosmetic.getType();
        this.ingredients = generateIngredient(cosmetic);
    }

    private List<IngredientResponseDto> generateIngredient(Cosmetic cosmetic) {
        return cosmetic.getCosmeticIngredients()
                .stream()
                .map(cosmeticIngredient -> IngredientResponseDto.from(cosmeticIngredient.getIngredient()))
                .collect(Collectors.toList());
    }

    public static CosmeticResponseDto from(Cosmetic cosmetic) {
        return new CosmeticResponseDto(cosmetic);
    }
}
