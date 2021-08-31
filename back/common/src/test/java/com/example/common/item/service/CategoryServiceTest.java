package com.example.common.item.service;

import com.example.common.item.api.dto.CategoryResponseDto;
import com.example.common.item.domain.Category;
import com.example.common.item.domain.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @DisplayName("카테고리 계층을 생성한다")
//    @Test
    void 카테고리_계층을_생성한다() {
        //given
        List<Category> categories = initCategory();
        given(categoryRepository.findAll())
                .willReturn(categories);
        //when
        CategoryResponseDto root = categoryService.findCategoriesHierarchy();

        //then
        verify(categoryRepository, atLeastOnce()).findAll();
        assertThat(root.getChildren().size()).isEqualTo(2); // root
        assertThat(root.getChildren().get(0).getChildren().size()).isEqualTo(2); // lv1 child
    }

    private List<Category> initCategory() {
        Category lv1 = new Category("1","lv1");
        Category lv2 = new Category("2","lv2");
        Category lv11 = new Category("3","lv11");
        Category lv12 = new Category("4","lv12");
        Category lv121 = new Category("5","lv121");
        Category lv21 = new Category("6","lv21");
        Category lv22 = new Category("7","lv22");
        ReflectionTestUtils.setField(lv1, "id", 1L);
        ReflectionTestUtils.setField(lv2, "id", 2L);
        ReflectionTestUtils.setField(lv11, "id", 3L);
        ReflectionTestUtils.setField(lv12, "id", 4L);
        ReflectionTestUtils.setField(lv121, "id", 5L);
        ReflectionTestUtils.setField(lv21, "id", 6L);
        ReflectionTestUtils.setField(lv22, "id", 7L);
        ReflectionTestUtils.setField(lv1, "parent", null);
        ReflectionTestUtils.setField(lv2, "parent", null);
        ReflectionTestUtils.setField(lv11, "parent", lv1);
        ReflectionTestUtils.setField(lv12, "parent", lv1);
        ReflectionTestUtils.setField(lv121, "parent", lv12);
        ReflectionTestUtils.setField(lv21, "parent", lv2);
        ReflectionTestUtils.setField(lv22, "parent", lv2);
        return Arrays.asList(lv1, lv2, lv11, lv12, lv121, lv21, lv22);
    }
}