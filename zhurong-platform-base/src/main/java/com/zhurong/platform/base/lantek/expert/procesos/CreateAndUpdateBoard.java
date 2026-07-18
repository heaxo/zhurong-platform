package com.zhurong.platform.base.lantek.expert.procesos;

public class CreateAndUpdateBoard extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String path;

    public CreateAndUpdateBoard(String path) {
        super(39);
        this.path = path;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + auto.code() + " " + quote(path);
    }
}