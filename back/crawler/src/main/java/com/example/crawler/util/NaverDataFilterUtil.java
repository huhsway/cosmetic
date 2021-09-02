package com.example.crawler.util;

import com.example.crawler.dto.ItemDto;

import java.util.List;
import java.util.stream.Collectors;

public class NaverDataFilterUtil {
    private NaverDataFilterUtil() {
    }

    public static List<ItemDto> itemFilter(List<ItemDto> naverItemList, String mallName, String category) {
        return naverItemList
                .stream()
                .filter(item -> !item.isEmpty() &&
                                item.isNormalProduct() &&
                                mallName.equals(item.getMallName()) &&
                                category.equals(item.getCategory1())
                ).collect(Collectors.toList());
    }
}