package com.zhurong.platform.security.jwt;

import java.security.SecureRandom;
import java.util.Base64;

public final class JwtSecretGenerator {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private JwtSecretGenerator() {
    }

    /**
     * 生成指定字节长度的 Base64 JWT Secret
     *
     * @param byteLength 建议 64（512 bit）
     * @return Base64 编码字符串
     */
    public static String generateBase64Secret(int byteLength) {

        if (byteLength < 32) {
            throw new IllegalArgumentException("JWT Secret 至少 32 字节（256 bit）");
        }

        byte[] bytes = new byte[byteLength];
        SECURE_RANDOM.nextBytes(bytes);

        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 推荐生成 64 字节（512 bit）密钥
     */
    public static String generateStrongSecret() {
        return generateBase64Secret(64);
    }

}
