package com.ao.platform.custom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom")
public class TestCustomController {

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-custom";
    }
}
