package com.ao.platform.core.lantek.entity;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/*
 * @Author a.he@lantek.com
 * @Description BaseEntity
 * @Date 2026/3/4 21:45
 **/
@Slf4j
public class BaseEntity {
    //兼容非lantek实体
    public Long getId() {
        try {
            Field field = this.getClass().getDeclaredField("RecID");
            field.setAccessible(true);
            return (Long) field.get(this);
        } catch (Exception e) {
            log.warn("Cannot get RecID", e);
        }
        return null;
    }
}
