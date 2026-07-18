package com.zhurong.platform.base.lantek.expert.procesos;

public class DisplayPrompt extends AutomationInstruction {

    private boolean stop;
    private String info = "";

    public DisplayPrompt() {
        super(22);
    }

    public DisplayPrompt stopExecutionProcessing() {
        stop = true;
        return this;
    }

    public DisplayPrompt setInfo(String info) {
        this.info = info;
        return this;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + bit(stop) + " " + quote(info);
    }
}