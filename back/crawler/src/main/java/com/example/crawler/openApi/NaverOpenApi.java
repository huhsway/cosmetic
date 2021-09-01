package com.example.crawler.openApi;

import com.example.crawler.constants.OpenAPIUrl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import static com.example.crawler.openApi.Connect.connect;
import static com.example.crawler.util.InputStreamReadUtil.convertInputStreamToString;
import static com.example.crawler.util.TextEncodeUtil.textEncode;

public class NaverOpenApi implements OpenApi {

    public String url;
    public String query;

    public String getData(String clientId, String clientSecret, String searchFilter, String searchWord) {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);  //어플리케이션 클라이언트 아이디값
        requestHeaders.put("X-Naver-Client-Secret", clientSecret); // 어플리케이션 클라이언트 시크릿값

        url = getUrl();
        query = getQuery(searchFilter, searchWord);

        String responseBody = responseBody(url + query, requestHeaders);

        return responseBody;
    }

    @Override
    public String getQuery(String searchFilter, String searchWord) {
        return textEncode(searchWord) + searchFilter;
    }

    @Override
    public String getUrl() {
        return OpenAPIUrl.NAVER_API_JSON.getCode(); // json 결과
    }

    private String responseBody(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return convertInputStreamToString(con.getInputStream());
            } else { // 에러 발생
                return convertInputStreamToString(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
}