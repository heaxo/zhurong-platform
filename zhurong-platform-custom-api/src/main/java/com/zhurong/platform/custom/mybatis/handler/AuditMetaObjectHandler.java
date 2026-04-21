package com.zhurong.platform.custom.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zhurong.platform.security.model.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AuditMetaObjectHandler implements MetaObjectHandler {

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            return null;
        }
        JwtUserDetails user = (JwtUserDetails) principal;
        return user.getUserId();
    }

    @Override
    public void insertFill(MetaObject metaObject) {

        Long userId = getCurrentUserId();

        this.strictInsertFill(metaObject, "createdAt",
                LocalDateTime.class, LocalDateTime.now());

        this.strictInsertFill(metaObject, "updatedAt",
                LocalDateTime.class, LocalDateTime.now());

        this.strictInsertFill(metaObject, "createdBy",
                Long.class, userId);

        this.strictInsertFill(metaObject, "updatedBy",
                Long.class, userId);

        this.strictInsertFill(metaObject, "version",
                Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        Long userId = getCurrentUserId();

        this.strictUpdateFill(metaObject, "updatedAt",
                LocalDateTime.class, LocalDateTime.now());

        this.strictUpdateFill(metaObject, "updatedBy",
                Long.class, userId);
    }
}
