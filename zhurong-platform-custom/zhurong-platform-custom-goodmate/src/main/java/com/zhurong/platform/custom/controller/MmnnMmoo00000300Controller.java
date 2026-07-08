package com.zhurong.platform.custom.controller;

import com.zhurong.platform.custom.convert.MmnnMmoo00000300Convert;
import com.zhurong.platform.custom.service.IMmnnMmoo00000300Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mmnnMmoo00000300")
public class MmnnMmoo00000300Controller extends com.zhurong.platform.custom.web.BaseController {

    private final MmnnMmoo00000300Convert convert;
    private final IMmnnMmoo00000300Service service;


}
