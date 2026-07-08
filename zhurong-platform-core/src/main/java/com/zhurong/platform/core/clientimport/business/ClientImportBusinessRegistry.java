package com.zhurong.platform.core.clientimport.business;

import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.entity.ClientImportRecordBase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@ConditionalOnClientCommunicationEnabled
public class ClientImportBusinessRegistry {

    /*
     * Spring自动收集所有业务处理器，避免新增业务时在多个service里维护switch分支。
     */
    private final Map<String, ClientImportBusinessHandler<?, ?>> handlers;

    public ClientImportBusinessRegistry(List<ClientImportBusinessHandler<?, ?>> handlers) {
        this.handlers = handlers.stream()
                .collect(Collectors.toMap(ClientImportBusinessHandler::businessType, Function.identity()));
    }

    @SuppressWarnings("unchecked")
    public <T, R extends ClientImportRecordBase> ClientImportBusinessHandler<T, R> get(String businessType) {
        ClientImportBusinessHandler<?, ?> handler = handlers.get(businessType);
        if (handler == null) {
            throw new BusinessException("CLIENT_IMPORT_BUSINESS_TYPE_UNSUPPORTED: 不支持的业务类型=" + businessType);
        }
        return (ClientImportBusinessHandler<T, R>) handler;
    }
}
