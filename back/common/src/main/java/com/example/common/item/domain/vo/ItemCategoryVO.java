package com.example.common.item.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ItemCategoryVO {
    private List<String[]> itemCategories;

    public ItemCategoryVO(List<String[]> itemCategories) {
        this.itemCategories = itemCategories;
    }
}
