package com.ao.platform.custom.feign;

import com.ao.platform.core.lantek.api.IMmnnMmoo00000300Api;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ao-platform-core")
public interface MmnnMmoo00000300FeignClient extends IMmnnMmoo00000300Api {

}