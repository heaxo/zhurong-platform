package com.zhurong.platform.codegenerator;

/*
 * @Author a.he@lantek.com
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
                "com.zhurong.platform.core",
                "lantek",
//                new String[]{"flyway_schema_history"},
                null,
                new String[]{
                        "AO_PLATFORM_JOB_CURSOR",
                        "AO_PLATFORM_OUTBOX_EVENT",
                        "AO_PLATFORM_NEST100_STATE_SNAPSHOT",
                        "DIS_MMNN_MMOO_00000200",
                        "DIS_MMTT_MMTT_00000100",
                        "DIS_NEST_NEST_00000100",
                        "DIS_NEST_NEST_00000200",
                        "DIS_NEST_NEST_00000300",
                        "DIS_NEST_NEST_00000400",
                        "DIS_NEST_NEST_00000500",
                        "DIS_PPTT_TTRR_00000700",
                        "DIS_PPTT_TTRR_00000800",
                        "DIS_PPTT_WWCC_00000800",
                        "DIS_SHPR_PPTT_00000200",
                        "MMNN_MMOO_00000100",
                        "MMNN_MMOO_00000300",
                        "PPRR_PPRR_00000100",
                        "SYST_OWND_00000100",
                        "SYST_FFLL_00000200",
                        "WWCC_WWCC_00000100"
                },
                true,
                true
        );

        GeneratorExecutor.execute(db, config);
    }
}
