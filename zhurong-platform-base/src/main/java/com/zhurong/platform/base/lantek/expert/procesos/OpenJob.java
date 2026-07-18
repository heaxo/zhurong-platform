package com.zhurong.platform.base.lantek.expert.procesos;

public class OpenJob extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String jobRef;

    public OpenJob(String jobRef) {
        super(4);
        this.jobRef = jobRef;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + auto.code() + " " + quote(jobRef);
    }
}