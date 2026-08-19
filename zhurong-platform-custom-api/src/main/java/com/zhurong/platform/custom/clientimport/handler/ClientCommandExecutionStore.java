package com.zhurong.platform.custom.clientimport.handler;

import java.util.function.Function;

/**
 * 可选的客户端命令幂等存储。客户模块可使用本机数据库实现，公共代理处理器会自动复用。
 */
public interface ClientCommandExecutionStore {

    ClientCommandResult executeOnce(
            String commandId,
            String commandType,
            Function<Boolean, ClientCommandResult> action
    );
}
