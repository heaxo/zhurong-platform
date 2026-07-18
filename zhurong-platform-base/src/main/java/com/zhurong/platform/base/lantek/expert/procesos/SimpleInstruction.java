package com.zhurong.platform.base.lantek.expert.procesos;

abstract class SimpleInstruction extends AutomationInstruction {

    protected SimpleInstruction(int instructionId) {
        super(instructionId);
    }

    @Override
    public String generateInstructionText() {
        return Integer.toString(getInstructionId());
    }
}