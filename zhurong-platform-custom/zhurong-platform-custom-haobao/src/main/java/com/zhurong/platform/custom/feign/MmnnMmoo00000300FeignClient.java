package com.zhurong.platform.custom.feign;

import com.zhurong.platform.core.lantek.api.IMmnnMmoo00000300Api;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "zhurong-platform-core",
        path = "/mmnnMmoo00000300",
        contextId = "MmnnMmoo00000300FeignClient")
public interface MmnnMmoo00000300FeignClient extends IMmnnMmoo00000300Api {

}