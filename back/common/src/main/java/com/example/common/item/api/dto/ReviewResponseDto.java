package com.example.common.item.api.dto;

import com.example.common.item.domain.cosmetic.review.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {
    private Long reviewId;
    private String title;
    private String content;

    private ReviewResponseDto(Review review) {
        this.reviewId = review.getReviewId();
        this.title = review.getTitle();
        this.content = review.getContent();
    }

    public static ReviewResponseDto from(Review review) {
        return new ReviewResponseDto(review);
    }
}
