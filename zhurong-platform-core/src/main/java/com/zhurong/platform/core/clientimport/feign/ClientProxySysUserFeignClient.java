package com.zhurong.platform.core.clientimport.feign;

import com.zhurong.platform.auth.api.ISysUserApi;
import com.zhurong.platform.core.configuration.CoreInternalFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

/** 读取当前登录账号绑定的客户端主机标识。 */
@FeignClient(
        name = "zhurong-platform-auth",
        contextId = "ClientProxySysUserFeignClient",
        configuration = CoreInternalFeignConfiguration.class
)
public interface ClientProxySysUserFeignClient extends ISysUserApi {
}
