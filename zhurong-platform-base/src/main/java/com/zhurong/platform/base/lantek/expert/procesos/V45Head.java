package com.zhurong.platform.base.lantek.expert.procesos;

public class V45Head extends AutomationInstruction {

    public V45Head() {
        super(0);
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " FILEPROLT 8.02";
    }
}