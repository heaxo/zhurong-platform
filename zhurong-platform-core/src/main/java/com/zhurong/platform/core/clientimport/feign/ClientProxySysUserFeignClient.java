package com.zhurong.platform.core.clientimport.feign;

import com.zhurong.platform.auth.vo.SysUserVO;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.core.configuration.CoreInternalFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/** 读取当前登录账号绑定的客户端主机标识。 */
@FeignClient(
        name = "zhurong-platform-auth",
        contextId = "ClientProxySysUserFeignClient",
        path = "/sysUser",
        configuration = CoreInternalFeignConfiguration.class
)
public interface ClientProxySysUserFeignClient {

    @GetMapping("/{id}")
    ApiResponse<SysUserVO> getById(@PathVariable("id") Long id);
}
