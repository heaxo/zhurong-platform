package com.zhurong.platform.custom.clientimport.feign;

import com.zhurong.platform.core.clientimport.api.IClientImportTaskApi;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import org.springframework.cloud.openfeign.FeignClient;

@ConditionalOnClientCommunicationEnabled
@FeignClient(
        name = "zhurong-platform-core",
        path = "/client-import/internal",
        contextId = "ClientImportCoreFeignClient")
public interface ClientImportCoreFeignClient extends IClientImportTaskApi {
}
