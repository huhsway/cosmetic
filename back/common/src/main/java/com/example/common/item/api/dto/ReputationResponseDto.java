package com.example.common.item.api.dto;

import com.example.common.item.domain.cosmetic.review.Reputation;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReputationResponseDto {
    private Long reputationId;
    private BigDecimal totalStarRatio;
    private int totalReviewCount;
    private BigDecimal fiveStarRatio;
    private BigDecimal fourStarRatio;
    private BigDecimal threeStarRatio;
    private BigDecimal twoStarRatio;
    private BigDecimal oneStarRatio;
    private List<ReviewResponseDto> reviews;

    private ReputationResponseDto(Reputation reputation) {
        this.reputationId = reputation.getReputationId();
        this.totalStarRatio = reputation.getTotalStarRatio();
        this.totalReviewCount = reputation.getTotalReviewCount();
        this.fiveStarRatio = reputation.getFiveStarRatio();
        this.fourStarRatio = reputation.getFourStarRatio();
        this.threeStarRatio = reputation.getThreeStarRatio();
        this.twoStarRatio = reputation.getTwoStarRatio();
        this.oneStarRatio = reputation.getOneStarRatio();
        this.reviews = reputation.getReviewList().stream()
                .map(ReviewResponseDto::from)
                .collect(Collectors.toList());
    }

    public static ReputationResponseDto from(Reputation reputation) {
        return new ReputationResponseDto(reputation);
    }
}
