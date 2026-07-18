package com.zhurong.platform.base.lantek.expert.procesos;

public class RemoveJob extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String jobRef;
    private boolean deleteRelatedElements;
    private boolean onlyParameterizedElementsAreRemoved;
    private boolean deleteJobRelatedProcessingInstructions;

    public RemoveJob(boolean removeCurrentJob) {
        this(removeCurrentJob, "");
    }

    public RemoveJob(boolean removeCurrentJob, String jobRef) {
        super(38);
        if (!removeCurrentJob && !valid(jobRef)) {
            throw new IllegalArgumentException("jobRef must be specified when removeCurrentJob is false.");
        }
        this.jobRef = jobRef;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + auto.code() + " " + quote(jobRef) + " "
                + bit(deleteRelatedElements) + " " + bit(onlyParameterizedElementsAreRemoved) + " "
                + bit(deleteJobRelatedProcessingInstructions);
    }

    public RemoveJob setDeleteRelatedElements(boolean value) { deleteRelatedElements = value; return this; }
    public RemoveJob setOnlyParameterizedElementsAreRemoved(boolean value) { onlyParameterizedElementsAreRemoved = value; return this; }
    public RemoveJob setDeleteJobRelatedProcessingInstructions(boolean value) { deleteJobRelatedProcessingInstructions = value; return this; }
}