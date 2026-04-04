package com.zhurong.platform.base.masterlink.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class XmlField {

    private final String fldRef;
    private final Object value;
    private final String fldType;

    public XmlField(String fldRef, Object value) {
        this(fldRef, value, null);
    }

    public XmlField(String fldRef, Object value, String fldType) {
        this.fldRef = fldRef;
        this.value = normalizeValue(value);
        this.fldType = fldType != null ? fldType : resolveType(value);
    }

    private Object normalizeValue(Object value) {
        if (value == null) return null;

        if (value instanceof Boolean b) {
            return b ? "1" : "0";
        }

        if (value instanceof LocalDate dt) {
            return dt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }

        return value;
    }

    private String resolveType(Object value) {
        if (value instanceof Boolean) return "10";
        if (value instanceof Integer || value instanceof Long) return "80";
        if (value instanceof Double || value instanceof Float) return "100";
        if (value instanceof LocalDate) return "120";
        return "20";
    }
}