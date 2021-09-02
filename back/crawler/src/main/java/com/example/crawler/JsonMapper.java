package com.example.crawler;

import com.example.crawler.dto.ItemDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.List;

public class JsonMapper<T> {
    private static final Gson gson = new Gson();

    public List<ItemDto> getDataByKey(String jsonString, String itemKey) throws JsonSyntaxException, JsonProcessingException {
        if (jsonString == null || jsonString.isEmpty()) {
            return Collections.emptyList();
        }

        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        if (jsonObject.isJsonNull() || !jsonObject.isJsonObject()) {
            return Collections.emptyList();
        }

        JsonArray items = (JsonArray) jsonObject.get(itemKey);
        if (items == null || items.isJsonNull()) {
            return Collections.emptyList();
        }

        List<ItemDto> jsonList = gson.fromJson(items.getAsJsonArray().toString(), new TypeToken<List<ItemDto>>() {
        }.getType());
        return jsonList;
    }
}