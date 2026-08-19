package com.zhurong.platform.custom.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 模板本机 HTTP 代理连通性测试接口。 */
@Hidden
@RestController
@RequestMapping("/custom")
public class TestCustomController {

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-custom";
    }
}
