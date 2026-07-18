package com.zhurong.platform.base.lantek.expert.procesos;

public class SaveAllAsSheets extends AutomationInstruction {

    private final boolean batchProcessing;
    private final Params params;
    private final String code;

    public enum Params {
        Program(1),
        Workshop(2);

        private final int code;

        Params(int code) {
            this.code = code;
        }

        public int code() {
            return code;
        }
    }

    public SaveAllAsSheets(boolean batchProcessing) {
        this(batchProcessing, "");
    }

    public SaveAllAsSheets(boolean batchProcessing, String code) {
        super(46);
        if (batchProcessing && !valid(code)) {
            throw new IllegalArgumentException("code must be specified for batch processing.");
        }
        this.batchProcessing = batchProcessing;
        this.code = code;
        this.params = Params.Program;
    }

    public SaveAllAsSheets(Params params) {
        super(46);
        this.batchProcessing = false;
        this.code = "";
        this.params = params;
    }

    @Override
    public String generateInstructionText() {
        if (batchProcessing) {
            return getInstructionId() + " 4 " + quote(code);
        }
        return getInstructionId() + " " + params.code() + " \"\"";
    }
}