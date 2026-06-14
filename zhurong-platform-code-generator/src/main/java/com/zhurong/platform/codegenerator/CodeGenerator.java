package com.zhurong.platform.codegenerator;

/*
 * @Author zhurong
 * @Description CodeGenerator
 * @Date 2026/2/17 21:36
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        DatabaseConfig db = new DatabaseConfig(
                "jdbc:sqlserver://lantekha\\Lantek;databaseName=LSystemDB;encrypt=false",
                "sa",
                ""
        );

        GeneratorConfig config = new GeneratorConfig(
                "com.zhurong.platform.custom",
                "lantek",
//                new String[]{"flyway_schema_history"},
                null,
                new String[]{
//                        "Zhurong_But_SupplierInfo",
//                        "Zhurong_But_Nesting_Parts_Split_Records",
//                        "ZHURONG_PLATFORM_JOB_CURSOR",
//                        "ZHURONG_PLATFORM_OUTBOX_EVENT",
//                        "ZHURONG_PLATFORM_NEST100_STATE_SNAPSHOT",
//                        "SYST_CCPP_00000100",
//                        "WWHH_WWHH_00000100",
//                        "WWHH_WWHH_00000200",
//                        "WWHH_IIVV_00000100",
//                        "WWHH_IIVV_00000200",
//                        "PPBB_PPBB_00000100",
//                        "DIS_MMNN_MMOO_00000200",
//                        "DIS_MMNN_BWSR_00000100",
//                        "DIS_MMNN_BWSR_00000200",
//                        "DIS_MMTT_MMTT_00000100",
//                        "DIS_NEST_NEST_00000100",
//                        "DIS_NEST_NEST_00000200",
//                        "DIS_NEST_NEST_00000300",
//                        "DIS_NEST_NEST_00000400",
//                        "DIS_NEST_NEST_00000500",
//                        "DIS_PPTT_TTRR_00000700",
//                        "DIS_PPTT_TTRR_00000800",
//                        "DIS_PPTT_WWCC_00000800",
//                        "DIS_SHPR_PPTT_00000200",
//                        "MMNN_MMOO_00000100",
                        "MMNN_MMOO_00000300",
//                        "PPRR_PPRR_00000100",
//                        "SYST_OWND_00000100",
//                        "SYST_FFLL_00000200",
//                        "WWCC_WWCC_00000100"
                },
                false,
                false
        );

        GeneratorExecutor.execute(db, config);
    }
}
