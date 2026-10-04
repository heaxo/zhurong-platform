package com.zhurong.platform.custom.feign;

import com.zhurong.platform.core.lantek.api.IWwhhWwhh00000100Api;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "zhurong-platform-core",
        path = "/wwhhWwhh00000100",
        contextId = "WwhhWwhh00000100FeignClient")
public interface WwhhWwhh00000100FeignClient extends IWwhhWwhh00000100Api {

}