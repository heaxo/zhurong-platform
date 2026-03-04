package com.ao.platform.codegenerator;

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
                "com.ao.platform",
                "core",
//                new String[]{"flyway_schema_history"},
                null,
                new String[]{"DIS_NEST_NEST_00000100"},
                true
        );

        GeneratorExecutor.execute(db, config);
    }
}
