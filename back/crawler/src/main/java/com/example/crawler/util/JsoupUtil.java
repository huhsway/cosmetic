package com.example.crawler.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupUtil {
    private JsoupUtil() {
    }

    public static Document getDocByUrl(String url) {
        Document doc = new Document("");
        try {
            doc = Jsoup.connect(url).timeout(10*1000).get();
        } catch (Exception e) {
            System.out.println(url);
            e.printStackTrace();
            reGetDocByUrl(url);
        }
        return doc;
    }

    public static Document reGetDocByUrl(String url) {
        Document doc = new Document("");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            doc = Jsoup.connect(url).timeout(10*1000).get();
        } catch (Exception e) {
            System.out.println(url);
            e.printStackTrace();
        }
        return doc;
    }
}
