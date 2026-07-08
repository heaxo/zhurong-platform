package com.zhurong.platform.core.clientimport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.core.clientimport.entity.ClientRegistry;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;

public interface ClientRegistryService extends IService<ClientRegistry> {

    void heartbeat(ClientImportTaskStatusMessage message);
}
