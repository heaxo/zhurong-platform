package com.zhurong.platform.custom.feign;

import com.zhurong.platform.core.lantek.api.IWwhhIivv00000100Api;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "zhurong-platform-core",
        path = "/wwhhIivv00000100",
        contextId = "WwhhIivv00000100FeignClient")
public interface WwhhIivv00000100FeignClient extends IWwhhIivv00000100Api {

}