package com.ao.platform.base.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuditMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        Long userId = getCurrentUserId();

        this.strictInsertFill(metaObject, "createTime",
                LocalDateTime.class, LocalDateTime.now());

        this.strictInsertFill(metaObject, "updateTime",
                LocalDateTime.class, LocalDateTime.now());

        this.strictInsertFill(metaObject, "createBy",
                Long.class, userId);

        this.strictInsertFill(metaObject, "updateBy",
                Long.class, userId);

        this.strictInsertFill(metaObject, "version",
                Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        Long userId = getCurrentUserId();

        this.strictUpdateFill(metaObject, "updateTime",
                LocalDateTime.class, LocalDateTime.now());

        this.strictUpdateFill(metaObject, "updateBy",
                Long.class, userId);
    }

    private Long getCurrentUserId() {
        // 从上下文获取当前用户ID
        return 1L;
    }
}
