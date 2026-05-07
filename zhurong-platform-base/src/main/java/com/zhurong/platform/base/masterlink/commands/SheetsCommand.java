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
public class SheetsCommand extends BaseXmlCommand implements GeometrySource {

    private final String commandName;
    private final String tableName = "PRODUCTS";

    public SheetsCommand() {
        this("IMPORT");
    }

    public SheetsCommand(String commandName) {
        this.commandName = commandName;
    }

    private String prdRef;
    private String prdName;
    private String disMatRef;
    private Float disThickness;
    private Float disLength;
    private Float disWidth;
    private Integer disCamQuan;

    private String disUData1Sht;
    private String disUData2Sht;
    private String disUData3Sht;

    private String geometryType;
    private String geometryPath;

    private Boolean disIsRemnant;

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
                new XmlField("REFERENCE", prdRef),
                new XmlField("NAME", prdName),
                new XmlField("PCategory", "51"),
                new XmlField("MATERIAL", disMatRef),
                new XmlField("LENGTH", disLength),
                new XmlField("WIDTH", disWidth),
                new XmlField("THICKNESS", disThickness),
                new XmlField("CAMQUANTITY", disCamQuan),
                new XmlField("SUDATA1", disUData1Sht),
                new XmlField("SUDATA2", disUData2Sht),
                new XmlField("SUDATA3", disUData3Sht),
                new XmlField("REMNANT", disIsRemnant)
        );
    }

    @Override
    public List<XmlConditions> getConditions() {
        return null;
    }

    // ===== GeometrySource =====

    @Override
    public String getPrdRef() {
        return prdRef;
    }

    @Override
    public String getGeometryType() {
        return geometryType;
    }

    @Override
    public String getGeometryPath() {
        return geometryPath;
    }

    // ===== setter（给 builder 用）=====

    public void setDIS_IsRemnant(Boolean value) {
        this.disIsRemnant = value;
    }

    public void setDIS_Length(Float value) {
        this.disLength = value;
    }

    public void setDIS_Width(Float value) {
        this.disWidth = value;
    }
}