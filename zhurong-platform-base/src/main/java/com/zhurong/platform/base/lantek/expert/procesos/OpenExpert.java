package com.zhurong.platform.base.lantek.expert.procesos;

public class OpenExpert extends AutomationInstruction {

    private final boolean silent;

    public OpenExpert() {
        this(false);
    }

    public OpenExpert(boolean silent) {
        super(1);
        this.silent = silent;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + bit(silent);
    }
}