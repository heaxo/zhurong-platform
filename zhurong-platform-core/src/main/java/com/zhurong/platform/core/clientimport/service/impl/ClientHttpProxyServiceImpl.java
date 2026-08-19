package com.zhurong.platform.core.clientimport.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.auth.vo.SysUserVO;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ClientCommunicationProperties;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.dto.ClientCommandRequest;
import com.zhurong.platform.core.clientimport.dto.ClientCommandResponse;
import com.zhurong.platform.core.clientimport.dto.ClientHttpProxyRequest;
import com.zhurong.platform.core.clientimport.dto.ClientHttpProxyResponse;
import com.zhurong.platform.core.clientimport.feign.ClientProxySysUserFeignClient;
import com.zhurong.platform.core.clientimport.mq.ClientCommandTypes;
import com.zhurong.platform.core.clientimport.mq.ClientIds;
import com.zhurong.platform.core.clientimport.service.ClientCommandService;
import com.zhurong.platform.core.clientimport.service.ClientHttpProxyService;
import com.zhurong.platform.core.clientimport.service.ClientRegistryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/** core 通用客户端 HTTP 代理；这里只处理路由和协议，不解释任何客户业务路径或载荷。 */
@Service
@RequiredArgsConstructor
@ConditionalOnClientCommunicationEnabled
public class ClientHttpProxyServiceImpl implements ClientHttpProxyService {

    private final ClientProxySysUserFeignClient userFeignClient;
    private final ClientRegistryService registryService;
    private final ClientCommandService commandService;
    private final ClientCommunicationProperties properties;
    private final ObjectMapper objectMapper;

    @Override
    public boolean available(Long userId) {
        try {
            registryService.requireOnline(resolveClientId(userId));
            return true;
        } catch (RuntimeException exception) {
            return false;
        }
    }

    @Override
    public ClientHttpProxyResponse execute(Long userId, ClientHttpProxyRequest proxyRequest) {
        String clientId = registryService.requireOnline(resolveClientId(userId));
        int timeoutSeconds = Math.min(600, Math.max(1, properties.getProxyTimeoutSeconds()));
        proxyRequest.setTimeoutSeconds(timeoutSeconds);

        ClientCommandRequest command = new ClientCommandRequest();
        command.setTargetClientId(clientId);
        command.setCommandType(ClientCommandTypes.HTTP_PROXY);
        command.setTimeoutSeconds(timeoutSeconds);
        command.setPayload(objectMapper.valueToTree(proxyRequest));

        ClientCommandResponse result = commandService.execute(command);
        if (result == null || !result.isSuccess() || result.getData() == null) {
            String message = result == null ? null : result.getMessage();
            throw new BusinessException(StringUtils.hasText(message)
                    ? message
                    : "客户端HTTP代理执行失败");
        }
        try {
            ClientHttpProxyResponse response = objectMapper.treeToValue(
                    result.getData(),
                    ClientHttpProxyResponse.class
            );
            if (response.getStatusCode() == null
                    || response.getStatusCode() < 100
                    || response.getStatusCode() > 999) {
                throw new BusinessException("客户端HTTP代理返回了无效状态码");
            }
            return response;
        } catch (JsonProcessingException exception) {
            throw new BusinessException("客户端HTTP代理响应解析失败");
        }
    }

    private String resolveClientId(Long userId) {
        if (userId == null) {
            throw new BusinessException("无法识别当前登录用户");
        }
        SysUserVO user = userFeignClient.getById(userId).unwrap();
        if (user == null || !StringUtils.hasText(user.getClientId())) {
            throw new BusinessException("当前登录账号尚未绑定客户端ID，请先在用户管理中配置客户端主机名");
        }
        return ClientIds.normalize(user.getClientId());
    }
}
