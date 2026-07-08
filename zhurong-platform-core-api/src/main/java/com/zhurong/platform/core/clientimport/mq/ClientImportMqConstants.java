package com.zhurong.platform.core.clientimport.mq;

public final class ClientImportMqConstants {

    private ClientImportMqConstants() {
    }

    public static final String COMMAND_EXCHANGE = "zhurong.platform.client.command.exchange";
    public static final String STATUS_EXCHANGE = "zhurong.platform.client.status.exchange";
    public static final String COMMAND_DLX = "zhurong.platform.client.command.dlx";
    public static final String STATUS_QUEUE = "zhurong.platform.client.status.queue";
    public static final String STATUS_ROUTING_KEY = "client.status";

    public static String commandQueue(String clientId) {
        return "zhurong.platform.client." + clientId + ".command";
    }

    public static String commandRoutingKey(String clientId) {
        return "client." + clientId;
    }
}
