package com.zhurong.platform.base.lantek.expert.procesos;

public class OpenDraw extends AutomationInstruction {

    private final boolean silent;

    public OpenDraw(boolean silent) {
        super(29);
        this.silent = silent;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + bit(silent);
    }
}