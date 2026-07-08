package com.zhurong.platform.core.clientimport.service;

import com.zhurong.platform.base.model.BaseEntity;

import java.time.LocalDateTime;

public final class EntityAuditHelper {

    private EntityAuditHelper() {
    }

    public static void prepareInsert(BaseEntity entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setDeleted(false);
        entity.setVersion(0);
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
    }

    public static void prepareUpdate(BaseEntity entity) {
        entity.setUpdateTime(LocalDateTime.now());
    }
}
