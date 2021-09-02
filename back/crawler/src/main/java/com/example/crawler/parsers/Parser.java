package com.example.crawler.parsers;

import org.jsoup.nodes.Document;

public interface Parser<T> {
    public T parse(Document doc);
}
