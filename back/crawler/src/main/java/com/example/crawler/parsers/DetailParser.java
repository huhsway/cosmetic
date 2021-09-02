package com.example.crawler.parsers;

import com.example.crawler.dto.CosmeticDto;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DetailParser<T> implements Parser<T> {

    private CosmeticDto cosmeticDto;

    @Override
    public T parse(Document html) {
        String detailSelector = "div.top_info_inner__1cEYE";
        String categorySelector = "div.top_breadcrumb__yrBH6 a";

        //카테고리 파싱
        Elements categoryElements = html.select(categorySelector);
        List<String[]> parsedCategory = parseCategoryInfo(categoryElements);

        //세부정보 파싱
        Elements detailElements = html.select(detailSelector);
        List<Map<String, String>> parsedDetail = parseDetailInfo(detailElements);

        cosmeticDto = new CosmeticDto(parsedCategory, parsedDetail);

        return (T)cosmeticDto;
    }

    private List<Map<String, String>> parseDetailInfo(Elements parsedElements) {
        List<String> detailElements = parsedElements.next().select("span.top_cell__3DnEV").eachText();

        List<Map<String, String>> list = detailElements
                .stream()
                .map(item -> {
                    Map<String, String> itemDetailMap = new HashMap<>();
                    String[] itemDetail = item.split(" : ");
                    /**
                     * @see com.daou.crawler.constants.NaverCosmeticItemFeature
                     */
                    String cosmeticFeatureKey = itemDetail[0];
                    String cosmeticFeatureValue = itemDetail[1];
                    itemDetailMap.put(cosmeticFeatureKey, cosmeticFeatureValue);
                    return itemDetailMap;
                }).collect(Collectors.toList());

        return list;
    }

    private List<String[]> parseCategoryInfo(Elements parsedElements) {
        List<String> category = parsedElements.eachText();
        List<String> catId = parsedElements.eachAttr("href");
        List<String[]> arrayList = new ArrayList<>();

        int size = category.size();
        if (category != null && catId != null) {
            for (int i = 0; i < size; i++) {
                String[] arr = new String[2];
                String categoryId = catId.get(i);
                arr[0] = categoryId.substring(categoryId.length() - 8);
                arr[1] = category.get(i);
                arrayList.add(arr);
            }
        }
        return arrayList;
    }
}
