package com.ao.platform.codegenerator;

public record GeneratorConfig(
        String basePackage,
        String moduleName,
        String[] excludeTables,
        String[] includeTables,
        boolean useDbColumnName
) {
    public GeneratorConfig(String basePackage, String moduleName) {
        this(basePackage, moduleName, null, null, false);
    }
    public GeneratorConfig(String basePackage, String moduleName,String[] excludeTables) {
        this(basePackage, moduleName, excludeTables, null, false);
    }
    public GeneratorConfig(String basePackage, String moduleName,String[] excludeTables,String[] includeTables) {
        this(basePackage, moduleName, excludeTables, includeTables, false);
    }

}
