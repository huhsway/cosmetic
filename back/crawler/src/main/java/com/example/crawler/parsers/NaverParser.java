package com.example.crawler.parsers;

import com.example.crawler.constants.OpenAPIUrl;
import com.example.crawler.dto.ItemDto;
import com.example.crawler.util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NaverParser {

    public List<Map<String, Object>> getItemLinkDoc(List<ItemDto> itemList) {
        List<ItemDto> redirectedLinkItemList = itemList.stream().map(item -> {
            item.setLink(getRedirectedLink(item));
            return item;
        }).collect(Collectors.toList());

        Map<String, Object> itemDocMap = new HashMap<>();
        List<Map<String, Object>> itemDocList = redirectedLinkItemList.stream().map(item -> {
            Document doc = JsoupUtil.getDocByUrl(item.getLink());
            itemDocMap.put("productId", item.getProductId());
            itemDocMap.put("doc", doc);
            return itemDocMap;
        }).collect(Collectors.toList());
        return itemDocList;
    }

    public String getRedirectedLink(ItemDto item) {
        String url = OpenAPIUrl.NAVER_SHOPPING.getCode();
        String itemLink = url +
                JsoupUtil.getDocByUrl(item.getLink())
                        .getElementsByTag("script")
                        .toString()
                        .split("'")[1];
        return itemLink;
    }
}