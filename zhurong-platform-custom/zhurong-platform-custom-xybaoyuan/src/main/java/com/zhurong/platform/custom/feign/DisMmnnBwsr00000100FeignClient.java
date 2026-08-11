package com.zhurong.platform.custom.feign;

import com.zhurong.platform.core.lantek.api.IDisMmnnBwsr00000100Api;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "zhurong-platform-core", path = "/disMmnnBwsr00000100", contextId = "xyDisMmnnBwsr00000100FeignClient")
public interface DisMmnnBwsr00000100FeignClient extends IDisMmnnBwsr00000100Api {}
