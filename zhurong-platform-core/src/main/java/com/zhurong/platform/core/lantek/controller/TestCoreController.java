package com.zhurong.platform.core.lantek.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core")
public class TestCoreController {

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-core";
    }
}
