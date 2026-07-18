package com.zhurong.platform.base.lantek.expert.procesos;

public class PrintJobReports extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String report;

    public PrintJobReports(String report) {
        super(33);
        this.report = report;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + auto.code() + " " + quote(report);
    }
}