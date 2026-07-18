package com.zhurong.platform.base.lantek.expert.procesos;

public enum AutomaticMode {
    Disable(0),
    Enable(1);

    private final int code;

    AutomaticMode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}