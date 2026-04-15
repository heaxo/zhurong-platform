package com.zhurong.platform.core.lantek.constants;

public class MCode {
    /// 套料辅助属性代码
    /// Nesting auxiliary properties code
    public static class DisNestNest00000200
    {
        /// 位移长度
        /// NB001
        public static String DisplacementLengthCode = "NB001";

        /// 无坡口切割长度
        /// NB002
        public static String BevelessCuttingLengthCode = "NB002";

        /// 穿孔数
        /// NC001
        public static String NumberOfPerforationsCode = "NC001";

        /// 位移时长（s）
        /// ND001
        public static String DisplacementDurationCode = "ND001";

        /// 普通切割时长（s）
        /// ND002
        public static String NormalCuttingDurationCode = "ND002";

        /// 穿孔时长（s）
        /// ND008
        public static String PerforationDurationCode = "ND008";
        /// 上下料时长（Auxiliar time （s））
        /// ND024
        public static String AuxiliarTimeCode = "ND024";
        /// 总加工时长（s）
        /// ND025
        public static String TotalProcessingTimeCode = "ND025";
        /// 材料成本
        /// NE001
        public static String CostOfMaterialCode = "NE001";

        /// 机器时间成本
        /// NE002
        public static String CostOfMachineTimeCode = "NE002";

        /// 消耗品成本
        /// NE003
        public static String CostOfConsumableCode = "NE003";

        /// 规划成本
        /// NE004
        public static String CostOfPlanningCode = "NE004";

        /// 总成本
        /// NE006
        public static String TotalCostCode = "NE006";

        /// 上料时长（s）
        /// NH001	Auxiliar time: Sheet upload 
        public static String SheetUploadAuxiliarTimeCode = "NH001";
        /// 下料时长（s）
        /// NH004	Auxiliar time: Sheet download 
        public static String SheetDownloadAuxiliarTimeCode = "NH004";
    }

    /// 钣金件辅助数据代码
    /// Sheet parts auxiliary data code
    public static class DisShprPptt00000200
    {
        
        /// 位移长度
        /// NB001
        public static String DisplacementLengthCode = "NB001";
        /// 冲压次数（Number of punches (no nibbling)）
        /// NC012
        public static String NumberOfPunchesCode = "NC012";
        /// 零件冲压次数
        /// NC015
        public static String PartNumberOfPunchesCode = "NC015";

        /// 位移时长（s）
        /// ND001
        public static String DisplacementDurationCode = "ND001";
        /// 零件冲压时长（s）
        /// ND020	Number of punches (no nibbling)
        public static String PartPunchesDurationCode = "ND020";
        /// 零件穿孔时长（s）
        /// ND023	Time of punch changes
        public static String PartPerforationDurationCode = "ND023";
        /// 总加工时长（s）
        /// ND025
        public static String TotalProcessingTimeCode = "ND025";
        /// 材料成本
        /// NE001
        public static String CostOfMaterialCode = "NE001";

        /// 机器时间成本
        /// NE002
        public static String CostOfMachineTimeCode = "NE002";

        /// 消耗品成本
        /// NE003
        public static String CostOfConsumableCode = "NE003";

        /// 规划成本
        /// NE004
        public static String CostOfPlanningCode = "NE004";

        /// 总成本
        /// NE006
        public static String TotalCostCode = "NE006";
  
        /// 零件带偏移的矩形重量（kg）
        /// NG004	Rectangular weight with offset
        public static String PartRectangularWeightWithOffsetCode = "NG004";
        /// 零件带偏移的外部重量（kg）
        /// NG005	External weight with offset
        public static String ExternalWeightWithOffsetCode = "NG005";
        /// 零件带偏移的实际重量（kg）
        /// NG006	Real weight with offset
        public static String RealWeightWithOffsetCode = "NG006";
        /// 偏移量（mm）
        /// NG007	Offset	
        public static String OffsetCode = "NG007";
        /// 最小孔半径（mm）
        /// NG008	Minimum radius for holes
        public static String MinimumRadiusForHolesCode = "NG008";
        /// 零件面积加的最大比例（mm）
        /// NG009	Maximum part area to be increased percentage
        public static String MaximumPartAreaToBeIncreasedPercentageCode = "NG009";
    }
}