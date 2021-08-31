package com.example.common.item.domain.cosmetic.review;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ReviewRepositoryTest {

    @Autowired
    private ReputationRepository reputationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @DisplayName("리뷰를_등록한다")
    @Test
    public void 리뷰를_등록한다(){
        Reputation reputation = reputationRepository.save(Reputation.builder()
                .totalStarRatio(new BigDecimal(4.7))
                .totalReviewCount(7892)
                .fiveStarRatio(new BigDecimal(79.70096300050685))
                .fourStarRatio(new BigDecimal(15.813482007095795))
                .threeStarRatio(new BigDecimal(3.9153573238722754))
                .twoStarRatio(new BigDecimal(0.2280790674100355))
                .oneStarRatio(new BigDecimal(0.3421186011150532))
                .build());

        Review review = Review.builder()
                .title("만족도")
                .content("너무 만족해요").build();
        review.setReputation(reputation);

        reviewRepository.save(review);
        List<Review> reviews = reviewRepository.findAll();

        assertThat(reviews.get(0).getTitle()).isEqualTo("만족도");
        assertThat(reviews.get(0).getReputation().getTotalReviewCount()).isEqualTo(7892);    }

}