package com.zhurong.platform.base.lantek.expert.procesos;

public class OpenDatabase extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String name;

    public OpenDatabase(String name) {
        super(42);
        this.name = name;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + auto.code() + " " + quote(name);
    }
}