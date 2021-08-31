package com.example.common.item.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    void before() {
        List<List<String[]>> itemCategories = Arrays.asList(
                Arrays.asList(new String[]{"50000002", "화장품/미용"}, new String[]{"50000190","스킨케어"},
                        new String[]{"50000438","로션"}),
                Arrays.asList(new String[]{"50000002", "화장품/미용"}, new String[]{"50000190","스킨케어"},
                        new String[]{"50000438","에센스"}),
                Arrays.asList(new String[]{"50000002", "화장품/미용"}, new String[]{"50000190","스킨케어"},
                        new String[]{"50000438","크림"}),
                Arrays.asList(new String[]{"50000002", "화장품/미용"}, new String[]{"50000195","색조메이크업"},
                        new String[]{"50001371","아이라이너"}, new String[]{"50004337","리퀴드형"}),
                Arrays.asList(new String[]{"50000002", "화장품/미용"}, new String[]{"50000190","스킨케어"},
                        new String[]{"50000440","크림"}),
                Arrays.asList(new String[]{"50000003", "디지털/가전"}, new String[]{"50000205","휴대폰액세서리"},
                        new String[]{"5001378","휴대폰보호필름"},new String[]{"5004595","액정보호필름"})
        );
        for (List<String[]> itemCategory : itemCategories) {
            setCategoryParent(itemCategory);
        }
    }

    @DisplayName("상품 카테고리를 등록한다")
    @Test
    void 상품_카테고리를_등록한다() {
        //given
        List<String[]> itemCategory = Arrays.asList(
                new String[]{"50000002", "화장품/미용"},
                new String[]{"50000191","선케어"},
                new String[]{"50000445","선크림"});
        //when
        Category category = setCategoryParent(itemCategory);

        //then
        assertThat(category.getName()).isEqualTo("선크림");
        assertThat(category.getItemCategoryId()).isEqualTo("50000445");
        assertThat(category.getParent().getName()).isEqualTo("선케어");
        assertThat(category.getParent().getItemCategoryId()).isEqualTo("50000191");
    }

    @DisplayName("상품 카테고리를 조회한다")
    @Test
    void 상품_카테고리를_조회한다() {
        //given
        List<Category> categories = categoryRepository.findAll();
        //when
        Map<Long, List<CategoryDto>> categoryGroup = createCategoryGroup(categories);
        //then
        for (Long parentId : categoryGroup.keySet()) {
            for (CategoryDto category :  categoryGroup.get(parentId)) {
                assertThat(parentId).isEqualTo(category.getParentId());
            }
        }
    }

    @Transactional
    @DisplayName("카테고리 아이디로 조회한다")
    @Test
    void 카테고리_아이디로_조회한다() {
        Optional<Category> findCategory = categoryRepository.findCategoryByItemCategoryId("50000190");
        Category category = findCategory.get();
        assertThat(category.getItemCategoryId()).isEqualTo("50000190");
        assertThat(category.getName()).isEqualTo("스킨케어");
    }

    @DisplayName("카테고리 그룹을 생성한다")
    @Test
    void 카테고리_그룹을_생성한다() {
        //given
        List<Category> categories = categoryRepository.findAll();
        //when
        Map<Long, List<CategoryDto>> categoryGroup = categories.stream()
                .map(category -> new CategoryDto(category.getId(),
                        category.getItemCategoryId(),
                        category.getName(),
                        category.getParent() == null ? 0L : category.getParent().getId()
                )).collect(groupingBy(CategoryDto::getParentId));
        //then
        for (Long parentId : categoryGroup.keySet()) {
            for (CategoryDto category : categoryGroup.get(parentId)) {
                assertThat(parentId).isEqualTo(category.getParentId());
            }
        }
    }

    @DisplayName("카테고리 계층을 생성한다")
    @Test
    void 카테고리_계층을_생성한다() {
        //given
        List<Category> categories = categoryRepository.findAll();
        //when
        Map<Long, List<CategoryDto>> categoryGroup = createCategoryGroup(categories);
        CategoryDto rootCategory = new CategoryDto(0L, "0", "Root", null);
        rootCategory = addChildGroup(rootCategory, categoryGroup);
        for (CategoryDto category : rootCategory.getChild()) {
            assertThat(rootCategory.getId()).isEqualTo(category.getParentId());
        }
    }

    private Category setCategoryParent(List<String[]> itemCategory) {
        Category parent = null;
        for (String[] itemCategoryInfo : itemCategory) {
            String itemCategoryId = itemCategoryInfo[0];
            String name = itemCategoryInfo[1];

            Category findCategory = categoryRepository.findCategoryByItemCategoryId(itemCategoryId).orElse(null);
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

    private Map<Long, List<CategoryDto>> createCategoryGroup(List<Category> categories) {
        return categories.stream()
                .map(category -> new CategoryDto(category.getId(),
                        category.getItemCategoryId(),
                        category.getName(),
                        category.getParent() == null ? 0L : category.getParent().getId()
                )).collect(groupingBy(CategoryDto::getParentId));
    }

    private CategoryDto addChildGroup(CategoryDto parent, Map<Long, List<CategoryDto>> categoryGroup) {
        List<CategoryDto> childCategories = categoryGroup.get(parent.getId());
        if (childCategories == null) // 종료 조건
            return parent;
        // parent 의 child 저장
        parent.setChild(childCategories);
        // child 재귀 동작
        childCategories.stream()
                .forEach(child -> addChildGroup(child, categoryGroup));
        return parent;
    }

    static class CategoryDto {
        private Long id;
        private String itemCategoryId;
        private String name;
        private Long parentId;
        private List<CategoryDto> child;

        public CategoryDto(Long id, String itemCategoryId, String name, Long parentId) {
            this.id = id;
            this.itemCategoryId = itemCategoryId;
            this.name = name;
            this.parentId = parentId;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getItemCategoryId() {
            return itemCategoryId;
        }

        public void setItemCategoryId(String itemCategoryId) {
            this.itemCategoryId = itemCategoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public List<CategoryDto> getChild() {
            return child;
        }

        public void setChild(List<CategoryDto> child) {
            this.child = child;
        }

        @Override
        public String toString() {
            return "CategoryDto{" +
                    "id=" + id +
                    ", itemCategoryId='" + itemCategoryId + '\'' +
                    ", name='" + name + '\'' +
                    ", parentId=" + parentId +
                    ", child=" + child +
                    '}';
        }
    }
}