package com.example.crawler.dto;

import com.example.common.item.domain.cosmetic.Cosmetic;
import com.example.common.item.domain.cosmetic.ingredient.Ingredient;
import com.example.crawler.constants.NaverCosmeticItemFeature;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class CosmeticDto {
    private String skinType;
    private String shape;
    private String useArea;
    private String volume;
    private String mainFeature;
    private String detailFeature;
    private String useTime;
    private String paRating;
    private String sunBlockRating;
    private String color;
    private String effect;
    private String type;
    private List<String[]> categories;
    private List<Ingredient> ingredient;

    public CosmeticDto setData(List<Ingredient> ingredientList){
        for(Ingredient ingredient : ingredientList){
            this.ingredient.add(ingredient);
        }
        return this;
    }

    public CosmeticDto(List<String[]> parsedCategory, List<Map<String,String>> parsedDetail) {
        this.categories = parsedCategory;
        parsedDetail.stream().forEach(item -> createCosmetic(item));
    }

    public CosmeticDto createCosmetic(Map<String, String> cosmetic) {
        for (String itemKey : cosmetic.keySet()) {
            this.setData(itemKey, cosmetic.get(itemKey));
        }
        return this;
    }

    public CosmeticDto setData(String itemKey, String itemValue) {

        if (NaverCosmeticItemFeature.MAIN_FEATURE.getCode().equals(itemKey)) {
            this.setMainFeature(itemValue);
        }
        if (NaverCosmeticItemFeature.SKIN_TYPE.getCode().equals(itemKey)) {
            this.setSkinType(itemValue);
        }
        if (NaverCosmeticItemFeature.SHAPE.getCode().equals(itemKey)) {
            this.setShape(itemValue);
        }
        if (NaverCosmeticItemFeature.VOLUME.getCode().equals(itemKey)) {
            this.setVolume(itemValue);
        }
        if (NaverCosmeticItemFeature.USE_AREA.getCode().equals(itemKey)) {
            this.setUseArea(itemValue);
        }
        if (NaverCosmeticItemFeature.PA_RATING.getCode().equals(itemKey)) {
            this.setPaRating(itemValue);
        }
        if (NaverCosmeticItemFeature.SUN_BLOCK_RATING.getCode().equals(itemKey)) {
            this.setSunBlockRating(itemValue);
        }
        if (NaverCosmeticItemFeature.COLOR.getCode().equals(itemKey)) {
            this.setColor(itemValue);
        }
        if (NaverCosmeticItemFeature.EFFECT.getCode().equals(itemKey)) {
            this.setEffect(itemValue);
        }
        if (NaverCosmeticItemFeature.TYPE.getCode().equals(itemKey)) {
            this.setType(itemValue);
        }
        if (NaverCosmeticItemFeature.DETAIL_FEATURE.getCode().equals(itemKey)) {
            this.setDetailFeature(itemValue);
        }
        return this;
    }

    public Cosmetic toEntity(ItemDto itemDto){
        return Cosmetic.builder()
                .productId(itemDto.getProductId())
                .title(itemDto.getTitle())
                .lprice(itemDto.getLprice())
                .hprice(itemDto.getHprice())
                .brand(itemDto.getBrand())
                .maker(itemDto.getMaker())
                .image(itemDto.getImage())
                .link(itemDto.getLink())
                .skinType(skinType)
                .shape(shape)
                .volume(volume)
                .mainFeature(mainFeature)
                .detailFeature(detailFeature)
                .useTime(useTime)
                .paRating(paRating)
                .sunBlockRating(sunBlockRating)
                .color(color)
                .effect(effect)
                .type(type)
                .build();
    }
}
