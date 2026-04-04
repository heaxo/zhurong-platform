package com.zhurong.platform.base.masterlink.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseXmlCommand implements IXmlCommand {

    protected List<XmlField> filterFields(List<XmlField> fields) {
        return fields.stream()
                .filter(f -> f.getValue() != null)
                .filter(f -> {
                    String str = f.getValue().toString();
                    return !str.isBlank();
                })
                .collect(Collectors.toList());
    }

    protected List<XmlField> filterFields(XmlField... fields) {
        return filterFields(Arrays.asList(fields));
    }
}