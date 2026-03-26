package com.zhurong.platform.codegenerator;

public record GeneratorConfig(
        String basePackage,
        String moduleName,
        String[] excludeTables,
        String[] includeTables,
        boolean useDbColumnName,
        boolean importLantekBaseEntity
) {
    public GeneratorConfig(String basePackage, String moduleName) {
        this(basePackage, moduleName, null, null, false,false);
    }
    public GeneratorConfig(String basePackage, String moduleName,String[] excludeTables) {
        this(basePackage, moduleName, excludeTables, null, false,false);
    }
    public GeneratorConfig(String basePackage, String moduleName,String[] excludeTables,String[] includeTables) {
        this(basePackage, moduleName, excludeTables, includeTables, false,false);
    }
    public GeneratorConfig(String basePackage, String moduleName,String[] excludeTables,String[] includeTables,boolean useDbColumnName) {
        this(basePackage, moduleName, excludeTables, includeTables, useDbColumnName,false);
    }

}
