package com.zhurong.platform.codegenerator;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GeneratorExecutor {

    private static String resolveOutputDir(String moduleName) {
        String root = System.getProperty("user.dir");
        return root.replace("\\", "/") + "/" + moduleName;
    }

    public static void execute(DatabaseConfig dbConfig, GeneratorConfig config) {

        String projectPath = resolveOutputDir("zhurong-platform-code-generator");
        String outputDir = projectPath + "/src/main/generated";

        FastAutoGenerator.create(
                        dbConfig.url(),
                        dbConfig.username(),
                        dbConfig.password()
                )
                .globalConfig(builder ->
                        builder
                                .author("me")
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
                .strategyConfig(builder -> {

                    if (config.excludeTables() != null && CollectionUtils.isNotEmpty(Arrays.asList(config.excludeTables()))){
                        builder.addExclude(config.excludeTables());
                    }
                    if (config.includeTables() != null && CollectionUtils.isNotEmpty(Arrays.asList(config.includeTables()))){
                        builder.addInclude(config.includeTables());
                    }
                    builder
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .injectionConfig(builder -> {

                    Map<String, Object> customMap = new HashMap<>();
                    customMap.put("useDbColumnName", config.useDbColumnName());
                    customMap.put("importLantekBaseEntity", config.importLantekBaseEntity());
                    customMap.put("moduleName", config.moduleName());
                    customMap.put("baseColumnList", true);
                    customMap.put("baseResultMap", true);

                    builder.customMap(customMap);
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
                    builder.customFile(new CustomFile.Builder()
                            .fileName("Data.ts")
                            .templatePath("/templates/data.vue.ftl")
                            .packageName("vue")
                            .enableFileOverride()
                            .build());
                    builder.customFile(new CustomFile.Builder()
                            .fileName("Index.vue")
                            .templatePath("/templates/index.vue.ftl")
                            .packageName("vue")
                            .enableFileOverride()
                            .build());
                    builder.customFile(new CustomFile.Builder()
                            .fileName("Request.ts")
                            .templatePath("/templates/request.ts.ftl")
                            .packageName("vue")
                            .enableFileOverride()
                            .build());
                    builder.customFile(new CustomFile.Builder()
                            .fileName("Typing.ts")
                            .templatePath("/templates/typing.ts.ftl")
                            .packageName("vue")
                            .enableFileOverride()
                            .build());
                    builder.customFile(new CustomFile.Builder()
                            .fileName("Form.vue")
                            .templatePath("/templates/form.vue.ftl")
                            .packageName("vue")
                            .enableFileOverride()
                            .build());
                })


                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
