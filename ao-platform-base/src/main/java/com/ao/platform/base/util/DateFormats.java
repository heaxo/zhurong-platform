package com.ao.platform.base.util;

import java.time.format.DateTimeFormatter;

/**
 * 时间格式常量
 */
public final class DateFormats {

    private DateFormats() {}

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DATETIME =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * yyyy-MM-dd
     */
    public static final DateTimeFormatter DATE =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * HH:mm:ss
     */
    public static final DateTimeFormatter TIME =
            DateTimeFormatter.ofPattern("HH:mm:ss");

}