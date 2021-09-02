package com.example.common.item.api;

import com.example.common.item.api.dto.CosmeticResponseDto;
import com.example.common.item.service.CosmeticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CosmeticApiController.COSMETIC_API_URI)
@RequiredArgsConstructor
public class CosmeticApiController {
    public static final String COSMETIC_API_URI = "/api/v1/items/cosmetics";

    private final CosmeticService cosmeticService;

    @GetMapping
    public ResponseEntity<List<CosmeticResponseDto>> findCosmetics() {
        List<CosmeticResponseDto> cosmetics = cosmeticService.findCosmetics();
        return ResponseEntity
                .ok()
                .body(cosmetics);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<CosmeticResponseDto> findCosmeticByItemId(@PathVariable("itemId") Long itemId) {
        CosmeticResponseDto cosmetics = cosmeticService.findCosmeticByItemId(itemId);
        return ResponseEntity
                .ok()
                .body(cosmetics);
    }
}
