package com.zhurong.platform.custom.service;

import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;

/**
 * 精工钢板库存允许导入Expert的仓库范围。
 * <p>
 * 新旧库存导入流程必须共用此白名单，避免两套校验规则发生偏差。
 */
public final class ScjinggongInventoryWarehousePolicy {

    public static final List<String> ALLOWED_CODES = List.of(
            "101", "102", "301", "310", "320", "A1001"
    );

    private ScjinggongInventoryWarehousePolicy() {
    }

    public static boolean isAllowed(String warehouseCode) {
        return StringUtils.hasText(warehouseCode)
                && ALLOWED_CODES.contains(normalize(warehouseCode));
    }

    public static String normalize(String warehouseCode) {
        return warehouseCode == null ? "" : warehouseCode.trim().toUpperCase(Locale.ROOT);
    }
}
