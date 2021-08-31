package com.example.common.item.service;

import com.example.common.item.domain.cosmetic.review.Reputation;
import com.example.common.item.domain.cosmetic.review.ReputationRepository;
import com.example.common.item.domain.cosmetic.review.Review;
import com.example.common.item.domain.cosmetic.review.ReviewRepository;
import com.example.common.item.domain.vo.ReputationReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReputationService implements ItemService<Reputation, ReputationReviewVO> {

    private final ReputationRepository reputationRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public Reputation save(ReputationReviewVO reputationReviewVO) {
        Reputation reputation = reputationReviewVO.getReputation();
        List<Review> reviews = reputationReviewVO.getReviews();

        reputation = reputationRepository.save(reputation);
        for(Review review : reviews){
            review.setReputation(reputation);
            reviewRepository.save(review);
        }
        return reputation;
    }
}
