package com.example.common.item.domain.cosmetic.review;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ReputationRepositoryTest {

    @Autowired
    private ReputationRepository reputationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @DisplayName("평판을_등록한다")
    @Test
    public void 평판을_등록한다(){
        Reputation reputation = reputationRepository.save(Reputation.builder()
                .totalStarRatio(new BigDecimal(4.7))
                .totalReviewCount(7892)
                .fiveStarRatio(new BigDecimal(79.70096300050685))
                .fourStarRatio(new BigDecimal(15.813482007095795))
                .threeStarRatio(new BigDecimal(3.9153573238722754))
                .twoStarRatio(new BigDecimal(0.2280790674100355))
                .oneStarRatio(new BigDecimal(0.3421186011150532))
                .build());

        List<Review> reviewList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Review review = Review.builder()
                    .title("리뷰" + i)
                    .content("가격도 싸고 너무 좋은거 같아요" + i)
                    .build();
            review.setReputation(reputation);
            reviewRepository.save(review);
        }
        reputation.setReviewList(reviewList);

        List<Reputation> reputations = reputationRepository.findAll();

        assertThat(reputations.get(0).getTotalStarRatio()).isEqualTo(reputation.getTotalStarRatio());
        assertThat(reputations.get(0).getTotalReviewCount()).isEqualTo(reputation.getTotalReviewCount());
        assertThat(reputations.get(0).getFiveStarRatio()).isEqualTo(reputation.getFiveStarRatio());
        assertThat(reputations.get(0).getFourStarRatio()).isEqualTo(reputation.getFourStarRatio());
        assertThat(reputations.get(0).getThreeStarRatio()).isEqualTo(reputation.getThreeStarRatio());
        assertThat(reputations.get(0).getTwoStarRatio()).isEqualTo(reputation.getTwoStarRatio());
        assertThat(reputations.get(0).getOneStarRatio()).isEqualTo(reputation.getOneStarRatio());
    }
}