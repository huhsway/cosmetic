package com.example.common.item.api;

import com.example.common.item.api.dto.IngredientResponseDto;
import com.example.common.item.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(IngredientApiController.INGREDIENT_API_URI)
@RequiredArgsConstructor
public class IngredientApiController {
    public static final String INGREDIENT_API_URI = "/api/v1/ingredients";

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientResponseDto>> findIngredientsWithFeature() {
        List<IngredientResponseDto> ingredients = ingredientService.findIngredientsWithFeature();
        return ResponseEntity
                .ok()
                .body(ingredients);
    }

}
