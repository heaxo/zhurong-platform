package com.zhurong.platform.base.masterlink.commands;

import com.zhurong.platform.base.masterlink.core.BaseXmlCommand;
import com.zhurong.platform.base.masterlink.core.XmlConditions;
import com.zhurong.platform.base.masterlink.core.XmlField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ManufacturingCommand extends BaseXmlCommand {

    private final String commandName;
    private static final String TABLE_NAME = "MANUFACTURING";

    public ManufacturingCommand() {
        this("IMPORT");
    }

    public ManufacturingCommand(String commandName) {
        this.commandName = commandName;
    }

    private LocalDate cDate;
    private LocalDate dDate;
    private LocalDate sDate;
    private LocalDate eDate;
    private String wrkRef;
    private String oprRef;
    private String ordRef;
    private String prdRefDst;
    private Integer rq;
    private Integer oLineNum;
    private String descrip;
    public String mnORef;
    private String disJobRef;
    private Integer priority;
    private String cusRef;
    private String cusName;
    private Boolean disIs2DSOp;
    private Boolean disIs3DSOp;
    private String disMatRef;
    private Float disThickness;
    private String disAutoNesting;
    private String disFillerPart;

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<XmlField> getFields() {
        return filterFields(
                new XmlField("Job", disJobRef),
                new XmlField("Reference", mnORef),
                new XmlField("Product", prdRefDst),
                new XmlField("Quantity", rq),
                new XmlField("Customer", cusRef),
                new XmlField("CusName", cusName),
                new XmlField("SaleOrder", ordRef),
                new XmlField("Material", disMatRef),
                new XmlField("Thickness", disThickness),
                new XmlField("WorkCenter", wrkRef),
                new XmlField("Operation", oprRef),
                new XmlField("PPriority", priority),
                new XmlField("FillerPart", disFillerPart),
                new XmlField("Description", descrip),
                new XmlField("CreationDate", cDate),
                new XmlField("DeliveryDate", dDate),
                new XmlField("StartDate", sDate),
                new XmlField("EndDate", eDate),
                new XmlField("SaleOrderLinenum", oLineNum),
                new XmlField("IS2DSOP", disIs2DSOp),
                new XmlField("IS3DSOP", disIs3DSOp),
                new XmlField("Automatic", disAutoNesting)
        );
    }

    @Override
    public List<XmlConditions> getConditions() {
        return null;
    }
}