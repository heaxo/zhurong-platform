package com.zhurong.platform.base.lantek.expert.procesos;

public class ExecutiveProgram extends AutomationInstruction {

    private boolean waitForThisProgramFinish;
    private final String path;

    public ExecutiveProgram(String path) {
        super(21);
        this.path = path;
    }

    public ExecutiveProgram waitProgramFinish() {
        waitForThisProgramFinish = true;
        return this;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + bit(waitForThisProgramFinish) + " " + quote(path);
    }
}