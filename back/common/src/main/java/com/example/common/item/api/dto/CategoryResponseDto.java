package com.example.common.item.api.dto;

import com.example.common.item.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CategoryResponseDto {
    private Long id;
    private String itemCategoryId;
    private String title;
    private Long parentId;
    private String link;
    private List<CategoryResponseDto> children;

    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.itemCategoryId = category.getItemCategoryId();
        this.title = category.getName();
        this.link = "/"+itemCategoryId;
        this.parentId = isParentNull(category.getParent());
    }

    private Long isParentNull(Category parent) {
        if (parent == null) return 0L;
        return parent.getId();
    }

    public static CategoryResponseDto from(Category category) {
        return new CategoryResponseDto(category);
    }
}
