package com.example.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonHomeController {

    @GetMapping("/common")
    public String home() {
        return "module: common server on";
    }
}
