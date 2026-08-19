package com.zhurong.platform.core.clientimport.target;

import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ClientCommunicationProperties;
import com.zhurong.platform.core.clientimport.mq.ClientIds;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class DefaultClientTargetResolver implements ClientTargetResolver {

    private final ClientCommunicationProperties properties;

    @Override
    public String resolve(ClientTargetResolveContext context) {
        // targetClientId解析顺序：请求显式指定 > 后续自定义规则预留位 > 配置默认客户端 > 明确失败。
        if (StringUtils.hasText(context.getRequestedTargetClientId())) {
            return ClientIds.normalize(context.getRequestedTargetClientId());
        }

        String resolvedByBusinessRule = resolveByBusinessRule(context);
        if (StringUtils.hasText(resolvedByBusinessRule)) {
            return ClientIds.normalize(resolvedByBusinessRule);
        }

        if (StringUtils.hasText(properties.getDefaultClientId())) {
            return ClientIds.normalize(properties.getDefaultClientId());
        }

        throw new BusinessException("TARGET_CLIENT_NOT_RESOLVED: 未能解析目标客户端，请传入targetClientId或配置zhurong.client-communication.default-client-id");
    }

    private String resolveByBusinessRule(ClientTargetResolveContext context) {
        // 后续扩展入口：可以根据businessType和标准业务数据匹配客户端，本次不实现真实匹配规则。
        return null;
    }
}
