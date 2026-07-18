package com.zhurong.platform.base.lantek.expert.procesos;

public class SaveAllSheets extends AutomationInstruction {

    private boolean print;

    public SaveAllSheets() {
        super(26);
    }

    public SaveAllSheets autoPrint() {
        print = true;
        return this;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + bit(print);
    }
}