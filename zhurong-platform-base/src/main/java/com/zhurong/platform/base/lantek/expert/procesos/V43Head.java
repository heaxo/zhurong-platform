package com.zhurong.platform.base.lantek.expert.procesos;

public class V43Head extends AutomationInstruction {

    public V43Head() {
        super(0);
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " FILEPROLT 8.02";
    }
}