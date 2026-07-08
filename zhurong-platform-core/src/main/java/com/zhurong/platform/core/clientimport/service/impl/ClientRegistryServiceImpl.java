package com.zhurong.platform.core.clientimport.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.entity.ClientRegistry;
import com.zhurong.platform.core.clientimport.mapper.ClientRegistryMapper;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;
import com.zhurong.platform.core.clientimport.service.ClientRegistryService;
import com.zhurong.platform.core.clientimport.service.EntityAuditHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@ConditionalOnClientCommunicationEnabled
public class ClientRegistryServiceImpl
        extends ServiceImpl<ClientRegistryMapper, ClientRegistry>
        implements ClientRegistryService {

    @Override
    public void heartbeat(ClientImportTaskStatusMessage message) {
        ClientRegistry registry = getOne(Wrappers.lambdaQuery(ClientRegistry.class)
                .eq(ClientRegistry::getClientId, message.getClientId()), false);
        if (registry == null) {
            registry = new ClientRegistry();
            registry.setClientId(message.getClientId());
            registry.setUserName(message.getUserName());
            registry.setClientVersion(message.getVersion());
            registry.setStatus("ONLINE");
            registry.setLastHeartbeatTime(LocalDateTime.now());
            EntityAuditHelper.prepareInsert(registry);
            save(registry);
            return;
        }
        registry.setUserName(message.getUserName());
        registry.setClientVersion(message.getVersion());
        registry.setStatus("ONLINE");
        registry.setLastHeartbeatTime(LocalDateTime.now());
        EntityAuditHelper.prepareUpdate(registry);
        updateById(registry);
    }
}
