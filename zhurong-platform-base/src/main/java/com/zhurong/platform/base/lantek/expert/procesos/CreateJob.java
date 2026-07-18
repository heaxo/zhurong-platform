package com.zhurong.platform.base.lantek.expert.procesos;

public class CreateJob extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String jobRef;
    private final String jobName;
    private final boolean createNewJobRef;
    private boolean promptErrorIfTheJobRefAlreadyExists;
    private String jobPath = "";
    private String udata1 = "";
    private String udata2 = "";
    private String udata3 = "";
    private String udata4 = "";
    private String udata5 = "";
    private String jobInfo = "";

    public CreateJob(String jobName, boolean createNewJobRef) {
        this(jobName, createNewJobRef, "");
    }

    public CreateJob(String jobName, boolean createNewJobRef, String jobRef) {
        super(3);
        if (!createNewJobRef && !valid(jobRef)) {
            throw new IllegalArgumentException("jobRef must be specified when automatic job reference creation is disabled.");
        }
        this.jobName = jobName;
        this.createNewJobRef = createNewJobRef;
        this.jobRef = jobRef;
    }

    @Override
    public String generateInstructionText() {
        return getInstructionId() + " " + auto.code() + " " + quote(jobRef) + " " + quote(jobName) + " "
                + quote(jobPath) + " " + quote(udata1) + " " + quote(udata2) + " " + quote(udata3) + " "
                + quote(udata4) + " " + quote(udata5) + " " + quote(jobInfo) + " "
                + bit(createNewJobRef) + " " + bit(promptErrorIfTheJobRefAlreadyExists);
    }

    public CreateJob setJobPath(String value) { jobPath = value; return this; }
    public CreateJob setUData1(String value) { udata1 = value; return this; }
    public CreateJob setUData2(String value) { udata2 = value; return this; }
    public CreateJob setUData3(String value) { udata3 = value; return this; }
    public CreateJob setUData4(String value) { udata4 = value; return this; }
    public CreateJob setUData5(String value) { udata5 = value; return this; }
    public CreateJob setJobInfo(String value) { jobInfo = value; return this; }
    public CreateJob setPromptErrorIfTheJobRefAlreadyExists(boolean value) { promptErrorIfTheJobRefAlreadyExists = value; return this; }
}