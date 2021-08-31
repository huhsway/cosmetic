package com.example.common.item.domain.cosmetic.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reputation_id")
    private Reputation reputation;

    @Builder
    public Review(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //==연관관계 메서드==//
    public void setReputation(Reputation reputation) {
        this.reputation = reputation;
        reputation.getReviewList().add(this);
    }
}