package com.zhurong.platform.core.lantek.constants;

public final class NestMqConstants {

    private NestMqConstants() {}

    public static final String EXCHANGE = "zhurong.nest.exchange";

    public static final class RoutingKey {
        public static final String STATE_CHANGED = "nest100.state.changed";
    }

    public static final class Queue {
        public static final String CUSTOM_STATE = "zhurong.custom.nest100.state.queue";
    }
}