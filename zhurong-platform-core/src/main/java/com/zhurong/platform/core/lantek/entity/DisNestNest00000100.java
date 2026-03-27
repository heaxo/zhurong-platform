package com.zhurong.platform.core.lantek.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 *
 * @author me
 * @since 2026-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("DIS_NEST_NEST_00000100")
public class DisNestNest00000100 extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableField("NstRef")
    private String NstRef;

    /**
     *
     */
    @TableField("NestMainRef")
    private String NestMainRef;

    /**
     *
     */
    @TableField("NestPrevRef")
    private String NestPrevRef;

    /**
     *
     */
    @TableField("NstPRef")
    private String NstPRef;

    /**
     *
     */
    @TableField("CopyIndx")
    private Integer CopyIndx;

    /**
     *
     */
    @TableField("WrkRef")
    private String WrkRef;

    /**
     *
     */
    @TableField("OprRef")
    private String OprRef;

    /**
     *
     */
    @TableField("NOrder")
    private Integer NOrder;

    /**
     *
     */
    @TableField("CDate")
    private LocalDateTime CDate;

    /**
     *
     */
    @TableField("JobRef")
    private String JobRef;

    /**
     *
     */
    @TableField("NCategory")
    private Integer NCategory;

    /**
     *
     */
    @TableField("MState")
    private Integer MState;

    /**
     *
     */
    @TableField("WosStatus")
    private Integer WosStatus;

    /**
     *
     */
    @TableField("CNC")
    private String CNC;

    /**
     *
     */
    @TableField("ShtRef")
    private String ShtRef;

    /**
     *
     */
    @TableField("ShtRefOrg")
    private String ShtRefOrg;

    /**
     *
     */
    @TableField("RealSht")
    private String RealSht;

    /**
     *
     */
    @TableField("MatRef")
    private String MatRef;

    /**
     *
     */
    @TableField("SLength")
    private Double SLength;

    /**
     *
     */
    @TableField("SWidth")
    private Double SWidth;

    /**
     *
     */
    @TableField("SThickness")
    private Double SThickness;

    /**
     *
     */
    @TableField("SArea")
    private Double SArea;

    /**
     *
     */
    @TableField("SUArea")
    private Double SUArea;

    /**
     *
     */
    @TableField("SWeight")
    private Double SWeight;

    /**
     *
     */
    @TableField("SUWeight")
    private Double SUWeight;

    /**
     *
     */
    @TableField("SXMax")
    private Double SXMax;

    /**
     *
     */
    @TableField("SYMax")
    private Double SYMax;

    /**
     *
     */
    @TableField("SPriority")
    private Double SPriority;

    /**
     *
     */
    @TableField("SProfit")
    private Double SProfit;

    /**
     *
     */
    @TableField("SProfitS")
    private Double SProfitS;

    /**
     *
     */
    @TableField("SMSQuant")
    private Integer SMSQuant;

    /**
     *
     */
    @TableField("ETime")
    private Double ETime;

    /**
     *
     */
    @TableField("Quantity")
    private Integer Quantity;

    /**
     *
     */
    @TableField("MQ")
    private Integer MQ;

    /**
     *
     */
    @TableField("RTime")
    private Double RTime;

    /**
     *
     */
    @TableField("SDate")
    private LocalDateTime SDate;

    /**
     *
     */
    @TableField("EDate")
    private LocalDateTime EDate;

    /**
     *
     */
    @TableField("UData1")
    private String UData1;

    /**
     *
     */
    @TableField("UData2")
    private String UData2;

    /**
     *
     */
    @TableField("UData3")
    private String UData3;

    /**
     *
     */
    @TableField("PartialSDate")
    private LocalDateTime PartialSDate;

    /**
     *
     */
    @TableField("EDuration")
    private Double EDuration;

    /**
     *
     */
    @TableField("IsCopy")
    private Byte IsCopy;

    /**
     *
     */
    @TableField("MltPrgRef")
    private String MltPrgRef;

    /**
     *
     */
    @TableField("MltPrgNstRef")
    private String MltPrgNstRef;

    /**
     *
     */
    @TableField("Descrip")
    private String Descrip;

    /**
     *
     */
    @TableField("ToPallet")
    private Byte ToPallet;

    /**
     *
     */
    @TableField("IsQuote")
    private Byte IsQuote;

    /**
     *
     */
    @TableField("RealTimeUpdated")
    private Byte RealTimeUpdated;

    /**
     *
     */
    @TableField("NstCpyRef")
    private String NstCpyRef;

    /**
     *
     */
    @TableField("UnitaryNest")
    private Byte UnitaryNest;

    /**
     *
     */
    @TableField("Var1")
    private String Var1;

    /**
     *
     */
    @TableField("Var2")
    private String Var2;

    /**
     *
     */
    @TableField("Var3")
    private String Var3;

    /**
     *
     */
    @TableField("Var4")
    private String Var4;

    /**
     *
     */
    @TableField("Var5")
    private String Var5;

    /**
     *
     */
    @TableField("CamLastDate")
    private LocalDateTime CamLastDate;

    /**
     *
     */
    @TableField("WrkCfg")
    private String WrkCfg;

    /**
     *
     */
    @TableField("RequiredDate")
    private LocalDateTime RequiredDate;

    /**
     *
     */
    @TableField("ScheduledStart")
    private LocalDateTime ScheduledStart;

    /**
     *
     */
    @TableField("Priority")
    private Integer Priority;

    /**
     *
     */
    @TableField("Name")
    private String Name;

    /**
     *
     */
    @TableField("MStateCloudStatus")
    private Integer MStateCloudStatus;

    /**
     *
     */
    @TableField("CuttingStatus")
    private Integer CuttingStatus;

    /**
     *
     */
    @TableField("CutQuantity")
    private Integer CutQuantity;

    /**
     *
     */
    @TableField("ExternalIndex")
    private String ExternalIndex;

    /**
     *
     */
    @TableField("Automatic")
    private Byte Automatic;

    /**
     *
     */
    @TableField("RecState")
    private Integer RecState;

    /**
     *
     */
    @TableField("CrtDate")
    private LocalDateTime CrtDate;

    /**
     *
     */
    @TableField("LastDate")
    private LocalDateTime LastDate;

    /**
     *
     */
    @TableField("CrtUser")
    private String CrtUser;

    /**
     *
     */
    @TableField("LastUser")
    private String LastUser;

    /**
     *
     */
    @TableField("Owner")
    private String Owner;

    /**
     *
     */
    @TableField("RecEnt")
    private String RecEnt;

    /**
     *
     */
    @TableField("RecOU")
    private String RecOU;

    /**
     *
     */
    @TableField("RecSec")
    private Integer RecSec;

    /**
     *
     */
    @TableField("CntID")
    private Integer CntID;

    /**
     *
     */
    @TableId(value = "RecID", type = IdType.AUTO)
    private Integer RecID;
}
