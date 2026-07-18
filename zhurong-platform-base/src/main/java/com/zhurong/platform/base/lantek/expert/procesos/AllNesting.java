package com.zhurong.platform.base.lantek.expert.procesos;

public class AllNesting extends AutomationInstruction {

    private boolean automaticProcessingSheet;
    private boolean singlePartNesting;
    private boolean useSheetListParams;
    private boolean newBoardAllowed;
    private String path;
    private boolean whenReNestingAllUseSameSheetList;
    private UseSheetListParams.HowUseNewSheet howToUseNewSheet = UseSheetListParams.HowUseNewSheet.DoNothing;

    public AllNesting() {
        super(24);
    }

    void setUseSheetListParam(UseSheetListParams useSheetList) {
        this.newBoardAllowed = useSheetList.isNewBoardAllowed();
        this.path = useSheetList.getPath();
        this.whenReNestingAllUseSameSheetList = useSheetList.isWhenReNestingAllUseSameSheetList();
        this.howToUseNewSheet = useSheetList.getHowToUseNewSheet();
    }

    @Override
    public String generateInstructionText() {
        if (useSheetListParams) {
            return getInstructionId() + " " + bit(automaticProcessingSheet) + " " + bit(singlePartNesting)
                    + " " + quote(path) + " " + (newBoardAllowed ? howToUseNewSheet.code() : 0)
                    + " " + bit(whenReNestingAllUseSameSheetList);
        }
        return getInstructionId() + " " + bit(automaticProcessingSheet) + " " + bit(singlePartNesting) + " \"\" 0 0";
    }

    public AllNesting useAutomaticProcessingSheet() {
        automaticProcessingSheet = true;
        return this;
    }

    public AllNesting useSinglePartNesting() {
        singlePartNesting = true;
        useSheetListParams = false;
        return this;
    }

    public UseSheetListParams useSheetList(String path) {
        singlePartNesting = false;
        useSheetListParams = true;
        return new UseSheetListParams(this, path);
    }

    public boolean isAutomaticProcessingSheet() {
        return automaticProcessingSheet;
    }

    public boolean isSinglePartNesting() {
        return singlePartNesting;
    }

    public boolean isNewBoardAllowed() {
        return newBoardAllowed;
    }

    public String getPath() {
        return path;
    }

    public boolean isWhenReNestingAllUseSameSheetList() {
        return whenReNestingAllUseSameSheetList;
    }

    public UseSheetListParams.HowUseNewSheet getHowToUseNewSheet() {
        return howToUseNewSheet;
    }
}