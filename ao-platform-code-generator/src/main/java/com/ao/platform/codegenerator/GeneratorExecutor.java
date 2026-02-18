package com.ao.platform.codegenerator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class GeneratorExecutor {

    private static String resolveOutputDir(String moduleName) {
        String root = System.getProperty("user.dir");
        return root.replace("\\", "/") + "/" + moduleName;
    }

    public static void execute(DatabaseConfig dbConfig, GeneratorConfig config) {

        String projectPath = resolveOutputDir("ao-platform-code-generator");
        String outputDir = projectPath + "/src/main/generated";

        FastAutoGenerator.create(
                        dbConfig.url(),
                        dbConfig.username(),
                        dbConfig.password()
                )
                .globalConfig(builder ->
                        builder
                                .author("heao")
                                .outputDir(outputDir)
                                .disableOpenDir()
                )
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((global, registry, metaInfo) -> {
                            if (metaInfo.getJdbcType().TYPE_CODE == Types.SMALLINT) {
                                return DbColumnType.INTEGER;
                            }
                            return registry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder
                                .parent(config.basePackage())
                                .moduleName(config.moduleName())
                                .pathInfo(Collections.singletonMap(OutputFile.xml, outputDir + "/mapper"))
                )
                .strategyConfig(builder ->
                        builder
                                .addExclude(config.excludeTables())
                                .entityBuilder()
                                .enableLombok()
                                .logicDeleteColumnName("deleted")
                                .controllerBuilder()
                                .enableRestStyle()
                )
                .injectionConfig(builder -> {

                    builder.customFile(new CustomFile.Builder()
                            .fileName("DTO.java")
                            .templatePath("/templates/dto.java.ftl")
                            .packageName("dto")
                            .build());

                    builder.customFile(new CustomFile.Builder()
                            .fileName("VO.java")
                            .templatePath("/templates/vo.java.ftl")
                            .packageName("vo")
                            .build());

                    builder.customFile(new CustomFile.Builder()
                            .fileName("Api.java")
                            .templatePath("/templates/api.java.ftl")
                            .packageName("api")
                            .formatNameFunction(tableInfo -> "I"
                                    + tableInfo.getEntityName())
                            .build());

                    builder.customFile(new CustomFile.Builder()
                            .fileName("Convert.java")
                            .templatePath("/templates/convert.java.ftl")
                            .packageName("convert")
                            .enableFileOverride()
                            .build());
                    builder.customFile(new CustomFile.Builder()
                            .fileName("PageQuery.java")
                            .templatePath("/templates/pageQuery.java.ftl")
                            .packageName("dto")
                            .enableFileOverride()
                            .build());
                })


                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
