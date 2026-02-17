package com.ao.platform.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TestAuthController {

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-auth";
    }
}
