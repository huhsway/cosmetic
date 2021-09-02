package com.example.crawler;

import com.example.crawler.dto.ItemDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapperTest {

    private JsonMapper mapper;
    private String dummy = "{\"lastBuildDate\": \"Sun, 14 Mar 2021 16:58:11 +0900\",\"total\": 1332754,\"start\": 25,\"display\": 2,\"items\": [{\"title\": \"제로이드 인텐시브 <b>로션</b> MD 200ml\",\"link\": \"https://search.shopping.naver.com/gate.nhn?id=22214572419\",\"image\": \"https://shopping-phinf.pstatic.net/main_2221457/22214572419.20200317115953.jpg\",\"lprice\": \"31850\",\"hprice\": \"34470\",\"mallName\": \"네이버\",\"productId\": \"22214572419\",\"productType\": \"1\",\"brand\": \"제로이드\",\"maker\": \"네오팜\",\"category1\": \"화장품/미용\",\"category2\": \"스킨케어\",\"category3\": \"로션\",\"category4\": \"\"},{\"title\": \"제로이드 쏘렉스 <b>로션</b> 350ml\",\"link\": \"https://search.shopping.naver.com/gate.nhn?id=18663217314\",\"image\": \"https://shopping-phinf.pstatic.net/main_1866321/18663217314.20190412112120.jpg\",\"lprice\": \"35000\",\"hprice\": \"55000\",\"mallName\": \"네이버\",\"productId\": \"18663217314\",\"productType\": \"1\",\"brand\": \"제로이드\",\"maker\": \"\",\"category1\": \"화장품/미용\",\"category2\": \"스킨케어\",\"category3\": \"로션\",\"category4\": \"\"}]}";

    @BeforeEach
    public void setUp() {
        mapper = new JsonMapper<ItemDto>();
    }

    @Test
    @DisplayName("유효한 param이 안넘어 올때")
    public void invalidatedParam() throws JsonProcessingException {
        // NaverOpen API is runned
        assertNull(mapper.getDataByKey(null, "items"));
        assertNull(mapper.getDataByKey("", "items"));
        assertThrows(JsonSyntaxException.class, () -> mapper.getDataByKey(new Date().toString(), "items"));
    }

    @Test
    @DisplayName("jspnString(response)에 키 값 테스트")
    public void notExistReferenceKey() throws JsonProcessingException {
        // NaverOpen API is runned
        List<ItemDto> items = mapper.getDataByKey(dummy, "items");
        assertEquals(2, items.size());

        List<ItemDto> notExistItems = mapper.getDataByKey(dummy, "item");
        assertNull(notExistItems);

        List<ItemDto> emptyStringItems = mapper.getDataByKey(dummy, "");
        assertNull(emptyStringItems);
    }

    @Test
    @DisplayName("Mapper에 의도한 객체에 mapping이 되는지")
    public void mappingTest() throws JsonProcessingException {
        // NaverOpen API is runned
        List<ItemDto> items = mapper.getDataByKey(dummy, "items");
        assertEquals(2, items.size());

        ItemDto item = items.get(0);
        assertEquals("https://search.shopping.naver.com/gate.nhn?id=22214572419", item.getLink());
    }
}