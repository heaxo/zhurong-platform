package com.zhurong.platform.custom.controller;

/*
 * @Author zhurong
 * @Description StaticResourcesController
 * @Date 2026/5/1 15:50
 **/

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staticResources")
public class StaticResourcesController extends BaseStaticResourcesController{
}
