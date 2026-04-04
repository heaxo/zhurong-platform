package com.zhurong.platform.base.masterlink.commands;

import com.zhurong.platform.base.masterlink.core.BaseXmlCommand;
import com.zhurong.platform.base.masterlink.core.XmlCondition;
import com.zhurong.platform.base.masterlink.core.XmlConditions;
import com.zhurong.platform.base.masterlink.core.XmlField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ManufacturingDeleteCommand extends BaseXmlCommand {

    private final ManufacturingCommand source;

    public ManufacturingDeleteCommand(ManufacturingCommand source) {
        this.source = source;
    }

    @Override
    public String getCommandName() {
        return "Delete";
    }

    @Override
    public String getTableName() {
        return "MANUFACTURING";
    }

    @Override
    public List<XmlField> getFields() {
        return List.of();
    }

    @Override
    public List<XmlConditions> getConditions() {
        XmlConditions block = new XmlConditions();

        for (XmlField f : source.getFields()) {
            XmlCondition c = new XmlCondition();
            c.setFieldRef(f.getFldRef());
            c.setValue(f.getValue());
            c.setFldType(f.getFldType());
            block.getConditions().add(c);
        }

        return List.of(block);
    }
}