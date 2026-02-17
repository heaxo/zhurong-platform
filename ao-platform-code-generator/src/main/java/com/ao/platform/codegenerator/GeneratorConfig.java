package com.ao.platform.codegenerator;

public record GeneratorConfig(
        String basePackage,
        String moduleName,
        String[] excludeTables
) {}
