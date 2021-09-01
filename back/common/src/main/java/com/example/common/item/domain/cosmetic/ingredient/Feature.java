package com.example.common.item.domain.cosmetic.ingredient;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id")
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    public enum Status {
        Good("good"),
        Warning("warning");

        private String status;
        Status(String status){
            this.status = status;
        }
    }

    @Builder
    public Feature(String name,Status status){
        this.name = name;
        this.status = status;
    }
}
