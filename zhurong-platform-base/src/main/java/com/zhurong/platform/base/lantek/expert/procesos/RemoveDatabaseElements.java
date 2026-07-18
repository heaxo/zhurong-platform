package com.zhurong.platform.base.lantek.expert.procesos;

public class RemoveDatabaseElements extends AutomationInstruction {

    private final AutomaticMode auto;
    private boolean deleteRelatedElements;
    private boolean onlyParameterizedElementsAreRemoved;
    private final String path;
    private final Argument argument;

    public enum Argument {
        Job(1),
        Catalogue(2),
        Graph(3),
        Part(3);

        private final int code;

        Argument(int code) {
            this.code = code;
        }

        public int code() {
            return code;
        }
    }

    public RemoveDatabaseElements(AutomaticMode auto, Argument argument) {
        this(auto, argument, "");
    }

    public RemoveDatabaseElements(AutomaticMode auto, Argument argument, String path) {
        super(44);
        if (auto == AutomaticMode.Enable && !valid(path)) {
            throw new IllegalArgumentException("path must be specified when automatic mode is enabled.");
        }
        this.auto = auto;
        this.argument = argument;
        this.path = path;
    }

    @Override
    public String generateInstructionText() {
        if (auto == AutomaticMode.Disable) {
            return getInstructionId() + " " + auto.code() + " " + argument.code() + " \"\"";
        }
        if (auto == AutomaticMode.Enable) {
            return getInstructionId() + " " + auto.code() + " " + argument.code() + " "
                    + bit(deleteRelatedElements) + " " + bit(onlyParameterizedElementsAreRemoved) + " " + quote(path);
        }
        throw new IllegalArgumentException("Invalid automatic mode.");
    }

    public RemoveDatabaseElements setDeleteRelatedElements(boolean value) {
        deleteRelatedElements = value;
        return this;
    }

    public RemoveDatabaseElements setOnlyParameterizedElementsAreRemoved(boolean value) {
        onlyParameterizedElementsAreRemoved = value;
        return this;
    }
}