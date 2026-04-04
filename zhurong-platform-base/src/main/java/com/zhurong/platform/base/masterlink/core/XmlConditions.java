package com.zhurong.platform.base.masterlink.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class XmlConditions {
    private final List<XmlCondition> conditions = new ArrayList<>();
}