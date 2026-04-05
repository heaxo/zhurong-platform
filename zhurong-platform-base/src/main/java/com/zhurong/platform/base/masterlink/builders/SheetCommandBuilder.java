package com.zhurong.platform.base.masterlink.builders;

import com.zhurong.platform.base.masterlink.commands.SheetsCommand;
import com.zhurong.platform.base.masterlink.core.IXmlCommand;

import java.util.ArrayList;
import java.util.List;

public class SheetCommandBuilder {

    private final List<IXmlCommand> commands = new ArrayList<>();

    public SheetCommandBuilder with(List<SheetsCommand> list) {
        commands.addAll(list);
        return this;
    }

    public List<IXmlCommand> build() {
        return commands;
    }
}