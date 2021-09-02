package com.example.crawler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerHomeController {

    @GetMapping("/crawler")
    public String home() {
        return "module: crawler server on";
    }
}
