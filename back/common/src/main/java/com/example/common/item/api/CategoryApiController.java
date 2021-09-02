package com.example.common.item.api;

import com.example.common.item.api.dto.CategoryResponseDto;
import com.example.common.item.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CategoryApiController.CATEGORY_API_URI)
@RequiredArgsConstructor
public class CategoryApiController {
    public static final String CATEGORY_API_URI = "/api/v1/categories";

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryResponseDto> findCategoriesHierarchy() {
        CategoryResponseDto categories = categoryService.findCategoriesHierarchy();
        return ResponseEntity
                .ok()
                .body(categories);
    }
}
