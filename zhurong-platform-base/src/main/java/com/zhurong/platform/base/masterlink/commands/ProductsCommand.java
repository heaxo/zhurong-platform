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
public class ProductsCommand extends BaseXmlCommand implements GeometrySource {

    private final String commandName;
    private final String tableName = "PRODUCTS";

    public ProductsCommand() {
        this("IMPORT");
    }

    public ProductsCommand(String commandName) {
        this.commandName = commandName;
    }

    // ===== 字段 =====
    private Short forSale;
    private Short assembly;
    private String prdRef;
    private String prdName;
    private Short pType;
    private Float weight;
    private String image;
    private Float stdCost;
    private String disPClass;
    private String disMatRef;
    private Float disLength;
    private Float disWidth;
    private Float disThickness;
    private Float disArea;
    private Float disPrice;
    private Float disCutPerim;
    private Float disMrkPerim;
    private Float disRectArea;
    private Float disExtArea;
    private Float disRectWeight;
    private Float disExtWeight;
    private String disFormatRef;
    private String disProfileRef;
    private Boolean disIsRemnant;
    private String disShtRefOrg;

    private String disUData1Prt;
    private String disUData2Prt;
    private String disUData3Prt;
    private String disUData4Prt;
    private String disUData5Prt;
    private String disUData6Prt;
    private String disUData7Prt;
    private String disUData8Prt;

    private String disUData1Sht;
    private String disUData2Sht;
    private String disUData3Sht;

    private Integer disCamQuan;
    private String disRotations;
    private Short disModelingBy;
    private String disModelingByID;

    private Short cstMethod;
    private Float disPrcRmntPrice;
    private Float disPrcScrpPrice;

    private String uCtName;
    private String untName;
    private String uscName;

    private Short disRPriority;
    private String product;

    private Float disWSA;
    private Float disWEA;
    private Float disFSA;
    private Float disFEA;

    private String disJobRef;

    private String geometryType;
    private String geometryPath;
    private String wrkRef;
    private String operation;

    // ===== override =====

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
                new XmlField("FORSALE", forSale),
                new XmlField("ASSEMBLY", assembly),
                new XmlField("REFERENCE", prdRef),
                new XmlField("NAME", prdName),
                new XmlField("PTYPE", pType),
                new XmlField("WEIGHT", weight),
                new XmlField("IMAGE", image),
                new XmlField("STDCOST", stdCost),
                new XmlField("PCATEGORY", disPClass),
                new XmlField("MATERIAL", disMatRef),
                new XmlField("LENGTH", disLength),
                new XmlField("WIDTH", disWidth),
                new XmlField("THICKNESS", disThickness),
                new XmlField("AREA", disArea),
                new XmlField("WEIGHTPRICE", disPrice),
                new XmlField("CUTPERIM", disCutPerim),
                new XmlField("MRKPERIM", disMrkPerim),
                new XmlField("RECTANGULARAREA", disRectArea),
                new XmlField("EXTERNALAREA", disExtArea),
                new XmlField("RECTANGULARWEIGHT", disRectWeight),
                new XmlField("EXTERNALWEIGHT", disExtWeight),
                new XmlField("FORMAT", disFormatRef),
                new XmlField("PROFILE", disProfileRef),
                new XmlField("ISREMNANT", disIsRemnant),
                new XmlField("ORIGINSHEET", disShtRefOrg),
                new XmlField("UDATA1", disUData1Prt),
                new XmlField("UDATA2", disUData2Prt),
                new XmlField("UDATA3", disUData3Prt),
                new XmlField("UDATA4", disUData4Prt),
                new XmlField("UDATA5", disUData5Prt),
                new XmlField("UDATA6", disUData6Prt),
                new XmlField("UDATA7", disUData7Prt),
                new XmlField("UDATA8", disUData8Prt),
                new XmlField("SUDATA1", disUData1Sht),
                new XmlField("SUDATA2", disUData2Sht),
                new XmlField("SUDATA3", disUData3Sht),
                new XmlField("CAMQUANTITY", disCamQuan),
                new XmlField("ROTATIONS", disRotations),
                new XmlField("MODELINGBY", disModelingBy),
                new XmlField("MODELINGBYID", disModelingByID),
                new XmlField("MSTOCK", cstMethod),
                new XmlField("REMNANTPRICE", disPrcRmntPrice),
                new XmlField("SCRAPPRICE", disPrcScrpPrice),
                new XmlField("UNITCATEGORY", uCtName),
                new XmlField("UNIT", untName),
                new XmlField("USCREF", uscName),
                new XmlField("PRIORITY", disRPriority),
                new XmlField("PRODUCT", product),
                new XmlField("WSA", disWSA),
                new XmlField("WEA", disWEA),
                new XmlField("FSA", disFSA),
                new XmlField("FEA", disFEA),
                new XmlField("JobRef", disJobRef)
        );
    }

    @Override
    public List<XmlConditions> getConditions() {
        return null;
    }

    // ===== GeometrySource实现 =====

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
}