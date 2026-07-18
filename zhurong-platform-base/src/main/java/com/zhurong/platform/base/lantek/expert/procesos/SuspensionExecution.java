package com.zhurong.platform.base.lantek.expert.procesos;

public class SuspensionExecution extends AutomationInstruction {

    private boolean notify;

    public SuspensionExecution() {
        super(9);
    }

    public SuspensionExecution notifyBeforeStoppingProcessing() {
        notify = true;
        return this;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + bit(notify);
    }
}