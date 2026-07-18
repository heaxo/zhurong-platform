package com.zhurong.platform.base.lantek.expert.procesos;

public class OpenPlating extends AutomationInstruction {

    private final boolean silent;

    public OpenPlating(boolean silent) {
        super(49);
        this.silent = silent;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + bit(silent);
    }
}