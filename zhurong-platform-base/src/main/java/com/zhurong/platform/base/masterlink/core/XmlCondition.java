package com.zhurong.platform.base.masterlink.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class XmlCondition {

    private String fieldRef;
    private String operator = "EQUAL";
    private Object value;
    private String fldType = "20";
}