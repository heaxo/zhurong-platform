package com.zhurong.platform.custom.clientimport.feign;

import com.zhurong.platform.core.clientimport.api.IClientImportTaskApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "zhurong-platform-core",
        path = "/client-import/internal",
        contextId = "ClientImportCoreFeignClient")
public interface ClientImportCoreFeignClient extends IClientImportTaskApi {
}
