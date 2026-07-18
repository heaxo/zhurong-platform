package com.zhurong.platform.base.lantek.expert.procesos;

public class OpenKit extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String jobRef;

    public OpenKit(String jobRef) {
        super(54);
        this.jobRef = jobRef;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + auto.code() + " " + quote(jobRef);
    }
}