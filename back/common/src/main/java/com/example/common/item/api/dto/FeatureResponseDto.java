package com.example.common.item.api.dto;

import com.example.common.item.domain.cosmetic.ingredient.Feature;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatureResponseDto {
    private Long id;
    private String name;
    private Feature.Status status;

    private FeatureResponseDto(Feature feature) {
        this.id = feature.getId();
        this.name = feature.getName();
        this.status = feature.getStatus();
    }

    public static FeatureResponseDto from(Feature feature) {
        return new FeatureResponseDto(feature);
    }
}
