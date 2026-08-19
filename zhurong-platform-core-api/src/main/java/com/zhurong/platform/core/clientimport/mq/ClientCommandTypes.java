package com.zhurong.platform.core.clientimport.mq;

/** 平台内置的通用客户端命令类型；客户专属命令仍由客户模块自行定义。 */
public final class ClientCommandTypes {

    public static final String HTTP_PROXY = "CLIENT_HTTP_PROXY";

    private ClientCommandTypes() {
    }
}
