package com.zhurong.platform.custom.clientimport.handler;

/**
 * 客户端通用命令处理器。命令类型和载荷类型由具体客户模块定义。
 */
public interface ClientCommandHandler<T> {

    String commandType();

    Class<T> payloadType();

    ClientCommandResult execute(String commandId, T payload);
}
