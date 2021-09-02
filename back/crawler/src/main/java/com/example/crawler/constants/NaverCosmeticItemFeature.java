package com.example.crawler.constants;

import lombok.Getter;

@Getter
public enum NaverCosmeticItemFeature implements EnumMapperType {
    SKIN_TYPE("피부타입"),
    USE_AREA("사용부위"),
    SHAPE("형태"),
    MAIN_FEATURE("주요제품특징"),
    DETAIL_FEATURE("세부제품특징"),
    VOLUME("용량"),
    USE_TIME("사용시간"),
    PA_RATING("PA지수"),
    SUN_BLOCK_RATING("자외선차단지수"),
    COLOR("색깔"),
    EFFECT("효과"),
    TYPE("타입");

    private final String feature;

    NaverCosmeticItemFeature(String feature) {
        this.feature = feature;
    }

    public String getName() {
        return name();
    }

    @Override
    public <T> String getCode() {
        return feature;
    }
}