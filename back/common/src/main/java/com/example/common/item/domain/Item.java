package com.example.common.item.domain;

import com.example.common.item.domain.cosmetic.review.Reputation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    private String productId;
    private String title;
    private String lprice;
    private String hprice;
    private String brand;
    private String maker;
    private String image;
    private String link;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reputation_id")
    private Reputation reputation;

    //==연관관계 메소드==//
    public void setCategory(Category category) {
        this.category = category;
    }

    public void setReputation(Reputation reputation) {
        this.reputation = reputation;
    }
}
