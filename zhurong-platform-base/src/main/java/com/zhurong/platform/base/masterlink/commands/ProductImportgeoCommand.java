package com.zhurong.platform.base.masterlink.commands;

import com.zhurong.platform.base.masterlink.core.BaseXmlCommand;
import com.zhurong.platform.base.masterlink.core.XmlConditions;
import com.zhurong.platform.base.masterlink.core.XmlField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductImportgeoCommand extends BaseXmlCommand {

    private final GeometrySource source;
    private final String commandName;

    public ProductImportgeoCommand(GeometrySource source) {
        this(source, "IMPORT");
    }

    public ProductImportgeoCommand(GeometrySource source, String commandName) {
        this.source = source;
        this.commandName = commandName;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getTableName() {
        return "IMPORTGEO";
    }

    @Override
    public List<XmlField> getFields() {
        return filterFields(
                new XmlField("Product", source.getPrdRef()),
                new XmlField("GeometryType", source.getGeometryType()),
                new XmlField("GeometryPath", source.getGeometryPath())
        );
    }

    @Override
    public List<XmlConditions> getConditions() {
        return null;
    }
}