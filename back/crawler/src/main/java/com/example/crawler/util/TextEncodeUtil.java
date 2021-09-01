package com.example.crawler.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TextEncodeUtil {
    private TextEncodeUtil() {
    }

    public static String textEncode(String searchWord) {
        String text = "";
        try {
            text = URLEncoder.encode(searchWord, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        return text;
    }
}
