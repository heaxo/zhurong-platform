package com.zhurong.platform.core.clientimport.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "zhurong.import-file")
public class ImportFileProperties {

    // core看到的物理归档目录；Windows开发通常是D:/Zhurong/import-files，Docker内通常是/app/data/import-files。
    private String storageRoot;

    private boolean autoCreateStorageRoot = false;

    private long maxFileSize = 104857600L;

    private int connectTimeoutSeconds = 20;

    private int readTimeoutSeconds = 120;

    // 客户端看到的共享目录根路径，例如\\SERVER\ZhurongImportFiles；账号和权限由Windows共享配置维护。
    private String windowsShareRoot;

    /*
     * 外部调用方传入的Windows共享源路径到core本地可读路径的映射。
     * 例如Docker内将\\SERVER\ZhurongImportFiles映射到/app/data/import-files。
     */
    private List<WindowsShareSourceMapping> windowsShareSourceMappings = new ArrayList<>();

    private boolean allowPrivateNetwork = false;

    private int maxRedirects = 5;

    @Getter
    @Setter
    public static class WindowsShareSourceMapping {

        private String shareRoot;

        private String mountedRoot;
    }
}
