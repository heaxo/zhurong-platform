package com.zhurong.platform.custom.feign;

import com.zhurong.platform.core.lantek.api.IDisNestNest00000100Api;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "zhurong-platform-core",
        path = "/disNestNest00000100",
        contextId = "DisNestNest00000100FeignClient")
public interface DisNestNest00000100FeignClient extends IDisNestNest00000100Api {

}