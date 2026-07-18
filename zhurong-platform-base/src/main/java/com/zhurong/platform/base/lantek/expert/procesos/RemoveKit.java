package com.zhurong.platform.base.lantek.expert.procesos;

public class RemoveKit extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String jobRef;
    private boolean deleteRelatedElements;
    private boolean onlyParameterizedElementsAreRemoved;
    private boolean deleteProductionOrdersRelatedToKits;

    public RemoveKit(boolean removeCurrentKit) {
        this(removeCurrentKit, "");
    }

    public RemoveKit(boolean removeCurrentKit, String jobRef) {
        super(56);
        if (!removeCurrentKit && !valid(jobRef)) {
            throw new IllegalArgumentException("jobRef must be specified when removeCurrentKit is false.");
        }
        this.jobRef = jobRef;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + auto.code() + " " + quote(jobRef) + " "
                + bit(deleteRelatedElements) + " " + bit(onlyParameterizedElementsAreRemoved) + " "
                + bit(deleteProductionOrdersRelatedToKits);
    }

    public RemoveKit setDeleteRelatedElements(boolean value) { deleteRelatedElements = value; return this; }
    public RemoveKit setOnlyParameterizedElementsAreRemoved(boolean value) { onlyParameterizedElementsAreRemoved = value; return this; }
    public RemoveKit setDeleteProductionOrdersRelatedToKits(boolean value) { deleteProductionOrdersRelatedToKits = value; return this; }
}