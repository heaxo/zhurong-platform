package com.zhurong.platform.custom.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "scjinggong")
public class ScjinggongProperties {
    private final Lantek lantek = new Lantek();
    private final U8 u8 = new U8();
    private final Feedback feedback = new Feedback();
    private final Inbound inbound = new Inbound();

    @Data
    public static class Lantek { private String install; }

    @Data
    public static class U8 {
        private String steelPlateUrl;
        private String secretKey;
    }

    @Data
    public static class Feedback {
        private String url;
        private String withdrawUrl;
        private String secretKey;
        private final Ftp ftp = new Ftp();
    }

    @Data
    public static class Ftp {
        private String host;
        private int port = 21;
        private String username;
        private String password;
        private String cncVirtualRoot;
        private String pdfVirtualRoot;
    }

    @Data
    public static class Inbound {
        private String basePartExcelDirectory;
        private String drawingRoot;
        private String drawingExtension = ".dxf";
    }
}
