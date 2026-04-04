package com.zhurong.platform.base.masterlink.core;

import java.util.List;

public interface IXmlCommand {

    String getCommandName();

    String getTableName();

    List<XmlField> getFields();

    List<XmlConditions> getConditions();
}