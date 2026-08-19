package com.zhurong.platform.core.clientimport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.core.clientimport.entity.ClientRegistry;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;

public interface ClientRegistryService extends IService<ClientRegistry> {

    void heartbeat(ClientImportTaskStatusMessage message);

    /** 校验指定客户端已注册且心跳未过期，返回规范化后的客户端标识。 */
    String requireOnline(String clientId);
}
