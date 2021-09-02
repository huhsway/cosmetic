package com.example.common.item.api;

import com.example.common.item.api.dto.ItemResponseDto;
import com.example.common.item.service.ItemCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ItemApiController.ITEM_API_URI)
@RequiredArgsConstructor
public class ItemApiController {
    public static final String ITEM_API_URI = "/api/v1/items";

    private final ItemCategoryService itemCategoryService;

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> findItems(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "30") int limit) {
        List<ItemResponseDto> items = itemCategoryService.findItemsPage(offset, limit);
        if (items.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity
                .ok()
                .body(items);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseDto> findItemById(@PathVariable("itemId") Long itemId) {
        ItemResponseDto item = itemCategoryService.findItemById(itemId);
        return ResponseEntity
                .ok()
                .body(item);
    }

    @GetMapping("/categories/{itemCategoryId}")
    public ResponseEntity<List<ItemResponseDto>> findItemsByItemCategoryId(
            @PathVariable("itemCategoryId") String itemCategoryId,
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "30") int limit) {
        List<ItemResponseDto> itemsInCategory = itemCategoryService.findItemsPageByItemCategoryId(itemCategoryId, offset, limit);
        return ResponseEntity
                .ok()
                .body(itemsInCategory);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemResponseDto>> findItemsByItemNameContains(@RequestParam("title") String title) {
        List<ItemResponseDto> items = itemCategoryService.findItemsByTitleContains(title);
        if (items.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity
                .ok()
                .body(items);
    }
}
