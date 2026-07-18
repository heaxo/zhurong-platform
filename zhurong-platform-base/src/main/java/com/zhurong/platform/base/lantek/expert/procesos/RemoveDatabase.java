package com.zhurong.platform.base.lantek.expert.procesos;

public class RemoveDatabase extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String name;

    public RemoveDatabase(String name) {
        super(43);
        this.name = name;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + auto.code() + " " + quote(name);
    }
}