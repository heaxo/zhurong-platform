package com.zhurong.platform.base.lantek.expert.procesos;

public class V44Head extends AutomationInstruction {

    public V44Head() {
        super(0);
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " FILEPROLT 8.02";
    }
}