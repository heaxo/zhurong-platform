package com.zhurong.platform.base.lantek.expert.procesos;

public class CreateKit extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final String jobRef;
    private final String jobName;
    private final boolean createNewJobRef;
    private boolean promptErrorIfTheKitAlreadyExists;
    private String jobPath = "";
    private String udata1 = "";
    private String udata2 = "";
    private String udata3 = "";
    private String udata4 = "";
    private String udata5 = "";
    private String jobInfo = "";

    public CreateKit(String jobName, boolean createNewJobRef) {
        this(jobName, createNewJobRef, "");
    }

    public CreateKit(String jobName, boolean createNewJobRef, String jobRef) {
        super(53);
        if (!createNewJobRef && !valid(jobRef)) {
            throw new IllegalArgumentException("jobRef must be specified when automatic kit reference creation is disabled.");
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
                + bit(createNewJobRef) + " " + bit(promptErrorIfTheKitAlreadyExists);
    }

    public CreateKit setJobPath(String value) { jobPath = value; return this; }
    public CreateKit setUData1(String value) { udata1 = value; return this; }
    public CreateKit setUData2(String value) { udata2 = value; return this; }
    public CreateKit setUData3(String value) { udata3 = value; return this; }
    public CreateKit setUData4(String value) { udata4 = value; return this; }
    public CreateKit setUData5(String value) { udata5 = value; return this; }
    public CreateKit setJobInfo(String value) { jobInfo = value; return this; }
    public CreateKit setPromptErrorIfTheKitAlreadyExists(boolean value) { promptErrorIfTheKitAlreadyExists = value; return this; }
}