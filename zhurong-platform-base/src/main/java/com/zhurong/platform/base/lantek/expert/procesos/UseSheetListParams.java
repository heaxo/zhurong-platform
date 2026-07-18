package com.zhurong.platform.base.lantek.expert.procesos;

public class UseSheetListParams {

    private final AllNesting allNesting;
    private boolean newBoardAllowed;
    private final String path;
    private boolean whenReNestingAllUseSameSheetList;
    private HowUseNewSheet howToUseNewSheet = HowUseNewSheet.DoNothing;

    public UseSheetListParams(AllNesting allNesting, String path) {
        this.allNesting = allNesting;
        this.path = path;
    }

    public UseSheetListParams allowedNewBoard() {
        newBoardAllowed = true;
        return this;
    }

    public UseSheetListParams reNestingAllUseSameSheetList() {
        whenReNestingAllUseSameSheetList = true;
        return this;
    }

    public UseSheetListParams setHowUseNewSheet(HowUseNewSheet value) {
        howToUseNewSheet = value;
        return allowedNewBoard();
    }

    public AllNesting build() {
        allNesting.setUseSheetListParam(this);
        return allNesting;
    }

    public AllNesting builde() {
        return build();
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

    public HowUseNewSheet getHowToUseNewSheet() {
        return howToUseNewSheet;
    }

    public enum HowUseNewSheet {
        Lock(1),
        Set0Quantity(2),
        DoNothing(3),
        Delete(4);

        private final int code;

        HowUseNewSheet(int code) {
            this.code = code;
        }

        public int code() {
            return code;
        }
    }
}