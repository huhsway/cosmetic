package com.example.crawler.constants;

public enum OpenAPIUrl implements EnumMapperType {
    //Naver
    NAVER_API_JSON("https://openapi.naver.com/v1/search/shop?query="),
    NAVER_API_XML("https://openapi.naver.com/v1/search/shop.xml?query="),
    NAVER_SHOPPING("https://search.shopping.naver.com");

    private String url;

    OpenAPIUrl(String url) {
        this.url = url;
    }

    @Override
    public <T> String getCode() {
        return url;
    }
}