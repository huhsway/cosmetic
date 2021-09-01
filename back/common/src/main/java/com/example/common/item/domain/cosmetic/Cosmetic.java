package com.example.common.item.domain.cosmetic;

import com.example.common.item.domain.Item;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorValue(value = "cosmetic")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cosmetic extends Item {
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

    @OneToMany(mappedBy = "cosmetic", cascade = CascadeType.ALL)
    private List<CosmeticIngredient> cosmeticIngredients = new ArrayList<>();

    @Builder
    public Cosmetic(String productId, String title, String lprice, String hprice, String brand,
                    String maker, String image, String link, String skinType, String shape,
                    String useArea, String volume, String mainFeature, String detailFeature,
                    String useTime, String paRating, String sunBlockRating,
                    String color, String effect, String type) {
        this.setProductId(productId);
        this.setTitle(title);
        this.setLprice(lprice);
        this.setHprice(hprice);
        this.setBrand(brand);
        this.setMaker(maker);
        this.setImage(image);
        this.setLink(link);
        this.skinType = skinType;
        this.shape = shape;
        this.useArea = useArea;
        this.volume = volume;
        this.mainFeature = mainFeature;
        this.detailFeature = detailFeature;
        this.useTime = useTime;
        this.paRating = paRating;
        this.sunBlockRating = sunBlockRating;
        this.color = color;
        this.effect = effect;
        this.type = type;
    }

    //==연관관계 매서드==//
    public void addCosmeticIngredient(CosmeticIngredient cosmeticIngredient) {
        cosmeticIngredients.add(cosmeticIngredient);
        cosmeticIngredient.setCosmetic(this);
    }
}
