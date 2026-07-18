package com.zhurong.platform.base.lantek.expert.procesos;

public class OpenNest extends AutomationInstruction {

    private final boolean silent;
    private final String nstRef;

    public OpenNest() {
        this(false, null);
    }

    public OpenNest(boolean silent) {
        this(silent, null);
    }

    public OpenNest(String nstRef) {
        this(false, nstRef);
    }

    public OpenNest(boolean silent, String nstRef) {
        super(23);
        this.silent = silent;
        this.nstRef = nstRef;
    }

    @Override
    public String generateInstructionText() {
        if (getHeader() instanceof V45Head) {
            return getInstructionId() + " " + bit(silent) + " " + quote(valid(nstRef) ? nstRef : "");
        }
        return getInstructionId() + " " + bit(silent);
    }
}