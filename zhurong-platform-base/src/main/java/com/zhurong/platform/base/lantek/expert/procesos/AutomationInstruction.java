package com.zhurong.platform.base.lantek.expert.procesos;

public abstract class AutomationInstruction {

    private final int instructionId;
    private AutomationInstruction header;

    protected AutomationInstruction(int instructionId) {
        this.instructionId = instructionId;
    }

    public int getInstructionId() {
        return instructionId;
    }

    public AutomationInstruction getHeader() {
        return header;
    }

    public void setHeader(AutomationInstruction header) {
        this.header = header;
    }

    public abstract String generateInstructionText();

    protected static int bit(boolean value) {
        return value ? 1 : 0;
    }

    protected static String quote(String value) {
        return "\"" + safe(value) + "\"";
    }

    protected static String safe(String value) {
        return value == null ? "" : value;
    }

    protected static boolean valid(String value) {
        return value != null && !value.isBlank();
    }
}