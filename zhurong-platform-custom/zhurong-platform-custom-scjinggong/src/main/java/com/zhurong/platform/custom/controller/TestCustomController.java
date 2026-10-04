package com.zhurong.platform.custom.controller;

import com.zhurong.platform.custom.feign.WwhhIivv00000100FeignClient;
import com.zhurong.platform.custom.feign.WwhhWwhh00000100FeignClient;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import com.zhurong.platform.custom.service.IWwhhIivv00000100Service;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
@RequiredArgsConstructor
@RequestMapping("/custom")
@Hidden
@Slf4j
public class TestCustomController {
    private final IPprrPprr00000100Service pprrPprr00000100Service;
    private final WwhhWwhh00000100FeignClient wwhhWwhh00000100FeignClient;
    private final WwhhIivv00000100FeignClient wwhhIivv00000100FeignClient;
    private final IWwhhIivv00000100Service iWwhhIivv00000100Service;

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-custom";
    }

    @GetMapping("/wait_test")
    public String waitTest() {
        try {
            TimeUnit.MINUTES.sleep(8);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "interrupted";
        }
        return "pong-from-custom";
    }

}
