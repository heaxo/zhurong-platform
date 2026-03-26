package com.zhurong.platform.auth.mybatis.handler;

import com.zhurong.platform.security.model.JwtUserDetails;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
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
}
