package com.zhurong.platform.custom.controller;

import com.zhurong.platform.custom.properties.LantekConfigProperties;
import com.zhurong.platform.custom.util.RegistryHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staticResources")
public class StaticResourcesController extends BaseStaticResourcesController {

    private final LantekConfigProperties lantekConfigProperties;

    private final Environment environment;

    private static String getDatabaseNameFromJdbcUrl(String jdbcUrl) {
        if (!StringUtils.hasText(jdbcUrl)) {
            return "";
        }

        /**
         * 支持 SQL Server URL：
         * jdbc:sqlserver://localhost:1433;databaseName=LSystemDB;encrypt=false
         * jdbc:sqlserver://localhost:1433;database=LSystemDB;encrypt=false
         */
        String[] parts = jdbcUrl.split(";");

        for (String part : parts) {
            int index = part.indexOf("=");

            if (index <= 0) {
                continue;
            }

            String key = part.substring(0, index).trim();
            String value = part.substring(index + 1).trim();

            if ("databaseName".equalsIgnoreCase(key)
                    || "database".equalsIgnoreCase(key)) {
                return normalizeJdbcValue(value);
            }
        }

        return "";
    }

    private static String normalizeJdbcValue(String value) {
        if (!StringUtils.hasText(value)) {
            return "";
        }

        /**
         * 兼容 databaseName={LSystemDB} 这种写法。
         */
        if (value.startsWith("{") && value.endsWith("}") && value.length() > 2) {
            return value.substring(1, value.length() - 1);
        }

        return value;
    }

    /**
     * 子类覆盖父类默认路径。
     * <p>
     * 组合规则：
     * lantek-config.install + 主数据库 databaseName
     * <p>
     * 例如：
     * C:\Lantek + LSystemDB = C:\Lantek\LSystemDB
     */
    @Override
    protected Path getStaticResourceRootFolder() {
        String install = lantekConfigProperties.getInstall();

        if (!StringUtils.hasText(install)) {
            install = RegistryHelper.getInstallDir();
        }

        String primary = environment.getProperty(
                "spring.datasource.dynamic.primary",
                "lantek"
        );

        String jdbcUrl = environment.getProperty(
                "spring.datasource.dynamic.datasource." + primary + ".url"
        );

        String databaseName = getDatabaseNameFromJdbcUrl(jdbcUrl);

        if (!StringUtils.hasText(databaseName)) {
            throw new IllegalStateException(
                    "主数据源 " + primary + " 的 JDBC URL 中未找到 databaseName：" + jdbcUrl
            );
        }

        return Paths.get(install)
                .resolve("Database")
                .resolve(databaseName)
                .toAbsolutePath()
                .normalize();
    }
}