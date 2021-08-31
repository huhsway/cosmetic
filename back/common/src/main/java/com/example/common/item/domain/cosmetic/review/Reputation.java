package com.example.common.item.domain.cosmetic.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "reputation")
public class Reputation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reputation_id")
    private Long reputationId;
    private BigDecimal totalStarRatio;
    private int totalReviewCount;
    private BigDecimal fiveStarRatio;
    private BigDecimal fourStarRatio;
    private BigDecimal threeStarRatio;
    private BigDecimal twoStarRatio;
    private BigDecimal oneStarRatio;

    @OneToMany(mappedBy = "reputation",cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @Builder
    public Reputation(BigDecimal totalStarRatio, int totalReviewCount,
                      BigDecimal fiveStarRatio, BigDecimal fourStarRatio,
                      BigDecimal threeStarRatio, BigDecimal twoStarRatio,
                      BigDecimal oneStarRatio){
        this.totalStarRatio = totalStarRatio;
        this.totalReviewCount = totalReviewCount;
        this.fiveStarRatio = fiveStarRatio;
        this.fourStarRatio = fourStarRatio;
        this.threeStarRatio = threeStarRatio;
        this.twoStarRatio = twoStarRatio;
        this.oneStarRatio = oneStarRatio;
    }
}