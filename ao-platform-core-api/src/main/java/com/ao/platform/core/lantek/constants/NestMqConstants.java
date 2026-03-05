package com.ao.platform.core.lantek.constants;

public final class NestMqConstants {

    private NestMqConstants() {}

    public static final String EXCHANGE = "ao.nest.exchange";

    public static final class RoutingKey {
        public static final String STATE_CHANGED = "nest100.state.changed";
    }

    public static final class Queue {
        public static final String CUSTOM_STATE = "ao.custom.nest100.state.queue";
    }
}