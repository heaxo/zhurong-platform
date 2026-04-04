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
public class JobsCommand extends BaseXmlCommand {

    private final String commandName;
    private final String tableName = "JOBS";

    public JobsCommand() {
        this("IMPORT");
    }

    public JobsCommand(String commandName) {
        this.commandName = commandName;
    }

    // ===== 字段 =====
    private String jobRef;
    private String jobName;
    private Float mState;

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<XmlField> getFields() {
        return filterFields(
                new XmlField("REFERENCE", jobRef),
                new XmlField("NAME", jobName),
                new XmlField("MSTATE", mState)
        );
    }

    @Override
    public List<XmlConditions> getConditions() {
        return null;
    }
}