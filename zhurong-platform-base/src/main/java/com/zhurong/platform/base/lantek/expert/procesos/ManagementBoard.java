package com.zhurong.platform.base.lantek.expert.procesos;

public class ManagementBoard extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String path;
    private final boolean allBoards;
    private final Argument argument;

    public enum Argument {
        Delete(1),
        Lock(2),
        Unlock(3);

        private final int code;

        Argument(int code) {
            this.code = code;
        }

        public int code() {
            return code;
        }
    }

    public ManagementBoard(boolean allBoards, Argument argument) {
        this(allBoards, argument, "");
    }

    public ManagementBoard(boolean allBoards, Argument argument, String path) {
        super(40);
        if (!allBoards && !valid(path)) {
            throw new IllegalArgumentException("path must be specified when allBoards is false.");
        }
        this.path = path;
        this.argument = argument;
        this.allBoards = allBoards;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + auto.code() + " " + argument.code() + " " + bit(allBoards) + " " + quote(path);
    }
}