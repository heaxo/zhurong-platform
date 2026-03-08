package com.ao.platform.custom.feign;

import com.ao.platform.core.lantek.api.IDisNestNest00000100Api;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "ao-platform-core",
        path = "/disNestNest00000100",
        contextId = "DisNestNest00000100FeignClient")
public interface DisNestNest00000100FeignClient extends IDisNestNest00000100Api {

}