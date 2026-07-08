package com.zhurong.platform.core.clientimport.service;

import com.zhurong.platform.base.model.BaseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class EntityAuditHelper {

    private static final ZoneId DEFAULT_ZONE = ZoneId.of("Asia/Shanghai");

    private EntityAuditHelper() {
    }

    public static LocalDateTime now() {
        return LocalDateTime.now(DEFAULT_ZONE);
    }

    public static void prepareInsert(BaseEntity entity) {
        LocalDateTime now = now();
        entity.setDeleted(false);
        entity.setVersion(0);
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
    }

    public static void prepareUpdate(BaseEntity entity) {
        entity.setUpdateTime(now());
    }
}
