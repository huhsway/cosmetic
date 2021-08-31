package com.example.common.item.service;

import com.example.common.item.api.dto.CategoryResponseDto;
import com.example.common.item.domain.Category;
import com.example.common.item.domain.CategoryRepository;
import com.example.common.item.domain.vo.ItemCategoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class CategoryService implements ItemService<Category, ItemCategoryVO> {

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Category save(ItemCategoryVO itemCategory) {
        return saveCategory(itemCategory);
    }

    private Category saveCategory(ItemCategoryVO itemCategoryVO) {
        Category parent = null;
        List<String[]> itemCategories = itemCategoryVO.getItemCategories();
        for (String[] itemCategoryInfo : itemCategories) {
            String itemCategoryId = itemCategoryInfo[0];
            String name = itemCategoryInfo[1];

            Category findCategory = categoryRepository.findCategoryByItemCategoryId(itemCategoryId)
                    .orElse(null);
            if (findCategory == null) { // 존재하지 않으면 신규 생성 및 부모 등록
                Category category = new Category(itemCategoryId, name);
                if (parent != null) {
                    parent.addChild(category);
                }
                parent = categoryRepository.save(category);
            }
            else { // 카테고리가 존재하면 부모 변경
                parent = findCategory;
            }
        }
        return parent;
    }

    @Transactional(readOnly = true)
    public CategoryResponseDto findCategoriesHierarchy() {
        List<Category> categories = categoryRepository.findAll();
        Map<Long, List<CategoryResponseDto>> categoryGroup = createCategoryGroup(categories);
        CategoryResponseDto root = new CategoryResponseDto(new Category(0L,"0", "root"));
        return addChildGroup(root, categoryGroup);
    }

    private Map<Long, List<CategoryResponseDto>> createCategoryGroup(List<Category> categories) {
         return categories.stream()
                 .map(CategoryResponseDto::new)
                 .collect(groupingBy(CategoryResponseDto::getParentId));
    }

    private CategoryResponseDto addChildGroup(CategoryResponseDto parent,
                                              Map<Long, List<CategoryResponseDto>> categoryGroup) {
        List<CategoryResponseDto> childCategories = categoryGroup.get(parent.getId());
        if (childCategories == null) // 종료 조건
            return parent;
        // parent 의 child 저장
        parent.setChildren(childCategories);
        // child 재귀 동작
        childCategories.stream()
                .forEach(child -> addChildGroup(child, categoryGroup));
        return parent;
    }
}
