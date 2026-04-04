package com.zhurong.platform.base.masterlink.builders;

import com.zhurong.platform.base.masterlink.commands.ProductImportgeoCommand;
import com.zhurong.platform.base.masterlink.commands.SheetsCommand;
import com.zhurong.platform.base.masterlink.core.IXmlCommand;

import java.util.ArrayList;
import java.util.List;

public class RemnantCommandBuilder {

    private final List<IXmlCommand> commands = new ArrayList<>();

    public RemnantCommandBuilder with(List<SheetsCommand> list) {
        for (SheetsCommand cmd : list) {
            cmd.setDIS_IsRemnant(true);
            cmd.setDIS_Length(0f);
            cmd.setDIS_Width(0f);

            commands.add(cmd);
            commands.add(new ProductImportgeoCommand(cmd));
        }
        return this;
    }

    public List<IXmlCommand> build() {
        return commands;
    }
}