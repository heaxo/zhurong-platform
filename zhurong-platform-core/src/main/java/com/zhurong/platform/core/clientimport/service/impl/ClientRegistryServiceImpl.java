package com.zhurong.platform.core.clientimport.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ClientCommunicationProperties;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.entity.ClientRegistry;
import com.zhurong.platform.core.clientimport.mapper.ClientRegistryMapper;
import com.zhurong.platform.core.clientimport.mq.ClientIds;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskStatusMessage;
import com.zhurong.platform.core.clientimport.service.ClientRegistryService;
import com.zhurong.platform.core.clientimport.service.EntityAuditHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientRegistryServiceImpl
        extends ServiceImpl<ClientRegistryMapper, ClientRegistry>
        implements ClientRegistryService {

    private final ClientCommunicationProperties properties;

    @Override
    public void heartbeat(ClientImportTaskStatusMessage message) {
        String clientId = ClientIds.normalize(message.getClientId());
        if (clientId == null) {
            return;
        }
        ClientRegistry registry = getOne(Wrappers.lambdaQuery(ClientRegistry.class)
                .eq(ClientRegistry::getClientId, clientId), false);
        if (registry == null) {
            registry = new ClientRegistry();
            registry.setClientId(clientId);
            registry.setUserName(message.getUserName());
            registry.setClientVersion(message.getVersion());
            registry.setStatus("ONLINE");
            registry.setLastHeartbeatTime(EntityAuditHelper.now());
            EntityAuditHelper.prepareInsert(registry);
            save(registry);
            return;
        }
        registry.setUserName(message.getUserName());
        registry.setClientVersion(message.getVersion());
        registry.setStatus("ONLINE");
        registry.setLastHeartbeatTime(EntityAuditHelper.now());
        EntityAuditHelper.prepareUpdate(registry);
        updateById(registry);
    }

    @Override
    public String requireOnline(String clientId) {
        String normalized = ClientIds.normalize(clientId);
        if (normalized == null) {
            throw new BusinessException("当前登录用户未绑定客户端ID");
        }
        ClientRegistry registry = getOne(Wrappers.lambdaQuery(ClientRegistry.class)
                .eq(ClientRegistry::getClientId, normalized), false);
        if (registry == null || registry.getLastHeartbeatTime() == null) {
            throw new BusinessException("客户端未注册或未启动: " + normalized);
        }
        LocalDateTime deadline = LocalDateTime.now()
                .minusSeconds(Math.max(1L, properties.getClientOnlineTimeoutSeconds()));
        if (!"ONLINE".equals(registry.getStatus())
                || registry.getLastHeartbeatTime().isBefore(deadline)) {
            throw new BusinessException("客户端已离线: " + normalized);
        }
        return normalized;
    }
}
