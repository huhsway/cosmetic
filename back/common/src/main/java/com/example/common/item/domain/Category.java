package com.example.common.item.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    private String itemCategoryId;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> child = new ArrayList<>();

    public Category(String itemCategoryId, String name) {
        this.itemCategoryId = itemCategoryId;
        this.name = name;
    }

    public Category(Long id, String itemCategoryId, String name) {
        this.id = id;
        this.itemCategoryId = itemCategoryId;
        this.name = name;
    }

    public void addChild(Category child) {
        this.getChild().add(child);
        child.parent = this;
    }
}
