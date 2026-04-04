package com.zhurong.platform.base.masterlink.commands;

import com.zhurong.platform.base.masterlink.core.BaseXmlCommand;
import com.zhurong.platform.base.masterlink.core.XmlCondition;
import com.zhurong.platform.base.masterlink.core.XmlConditions;
import com.zhurong.platform.base.masterlink.core.XmlField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ManufacturingUpdateCommand extends BaseXmlCommand {

    private final ManufacturingCommand source;

    private static final Set<String> CONDITIONS = Set.of("REFERENCE");

    public ManufacturingUpdateCommand(ManufacturingCommand source) {
        this.source = source;
    }

    @Override
    public String getCommandName() {
        return "Update";
    }

    @Override
    public String getTableName() {
        return "MANUFACTURING";
    }

    /**
     * UPDATE SET 字段（排除条件字段）
     */
    @Override
    public List<XmlField> getFields() {
        return source.getFields().stream()
                .filter(f -> !containsIgnoreCase(CONDITIONS, f.getFldRef()))
                .collect(Collectors.toList());
    }

    /**
     * WHERE 条件字段
     */
    @Override
    public List<XmlConditions> getConditions() {

        XmlConditions block = new XmlConditions();

        List<XmlField> conditionFields = source.getFields().stream()
                .filter(f -> containsIgnoreCase(CONDITIONS, f.getFldRef()))
                .toList();

        for (XmlField f : conditionFields) {
            XmlCondition c = new XmlCondition();
            c.setFieldRef(f.getFldRef());
            c.setOperator("EQUAL");
            c.setValue(f.getValue());
            c.setFldType(f.getFldType());

            block.getConditions().add(c);
        }

        return List.of(block);
    }

    // ===== 工具方法 =====

    private boolean containsIgnoreCase(Set<String> set, String value) {
        return set.stream().anyMatch(s -> s.equalsIgnoreCase(value));
    }
}