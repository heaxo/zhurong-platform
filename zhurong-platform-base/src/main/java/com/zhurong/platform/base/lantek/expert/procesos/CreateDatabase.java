package com.zhurong.platform.base.lantek.expert.procesos;

public class CreateDatabase extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String name;
    private final String path;

    public CreateDatabase(String name, String path) {
        super(41);
        this.name = name;
        this.path = path;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + auto.code() + " " + quote(name) + " " + quote(path) + " \"\"";
    }
}