package com.ao.platform.codegenerator;

/*
 * @Author a.he@lantek.com
 * @Description CodeGenerator
 * @Date 2026/2/17 21:36
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        DatabaseConfig db = new DatabaseConfig(
                "jdbc:postgresql://lantekha:5432/ao_platform_auth",
                "postgres",
                "123456"
        );

        GeneratorConfig config = new GeneratorConfig(
                "com.ao.platform",
                "auth",
                new String[]{"flyway_schema_history"}
        );

        GeneratorExecutor.execute(db, config);
    }
}
