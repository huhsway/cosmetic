package com.example.common.item.domain.vo;

import com.example.common.item.domain.cosmetic.review.Reputation;
import com.example.common.item.domain.cosmetic.review.Review;
import lombok.Getter;

import java.util.List;

@Getter
public class ReputationReviewVO {
    private Reputation reputation;
    private List<Review> reviews;

    public ReputationReviewVO(Reputation reputation, List<Review> reviews) {
        this.reputation = reputation;
        this.reviews = reviews;
    }
}
