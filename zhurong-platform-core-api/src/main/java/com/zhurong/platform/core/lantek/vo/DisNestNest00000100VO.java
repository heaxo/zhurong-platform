package com.zhurong.platform.core.lantek.vo;

import com.zhurong.platform.core.lantek.dto.NestingAuxiliaryProperties;
import com.zhurong.platform.core.lantek.dto.NestingDocument;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * VO
 */
@Data
public class DisNestNest00000100VO implements Serializable {

    private List<DisNestNest00000300VO> nestRemnant;
    private List<DisNestNest00000500VO> nestParts;
    private List<MmnnMmoo00000300VO> jobParts;
    private DisMmnnMmoo00000200VO job;
    private NestingAuxiliaryProperties nestingAuxiliaryProperties;
    private NestingDocument nestingDocument;
    private Double partWeight;
    private Double partArea;

    private String CNCPath;
    private String WMFPath;
    private String fullWMFPath;
    public Double getPartWeight(){
        if (nestParts == null || nestParts.isEmpty()){
            return 0D;
        }
        return nestParts.stream().mapToDouble(it -> {
            if (it.getItem() != null){
                return it.getQuantity() * it.getItem().getWeight();
            }
            return 0D;
        }).sum();
    }
    public Double getPartArea(){
        if (nestParts == null || nestParts.isEmpty()){
            return 0D;
        }
        return nestParts.stream().mapToDouble(it -> {
            if (it.getItem() != null){
                return it.getQuantity() * it.getItem().getDIS_Area();
            }
            return 0D;
        }).sum();
    }



    private String NstRef;
    private String NestMainRef;
    private String NestPrevRef;
    private String NstPRef;
    private Integer CopyIndx;
    private String WrkRef;
    private String OprRef;
    private Integer NOrder;
    private LocalDateTime CDate;
    private String JobRef;
    private Integer NCategory;
    private Integer MState;
    private Integer WosStatus;
    private String CNC;
    private String ShtRef;
    private String ShtRefOrg;
    private String RealSht;
    private String MatRef;
    private Double SLength;
    private Double SWidth;
    private Double SThickness;
    private Double SArea;
    private Double SUArea;
    private Double SWeight;
    private Double SUWeight;
    private Double SXMax;
    private Double SYMax;
    private Double SPriority;
    private Double SProfit;
    private Double SProfitS;
    private Integer SMSQuant;
    private Double ETime;
    private Integer Quantity;
    private Integer MQ;
    private Double RTime;
    private LocalDateTime SDate;
    private LocalDateTime EDate;
    private String UData1;
    private String UData2;
    private String UData3;
    private LocalDateTime PartialSDate;
    private Double EDuration;
    private Byte IsCopy;
    private String MltPrgRef;
    private String MltPrgNstRef;
    private String Descrip;
    private Byte ToPallet;
    private Byte IsQuote;
    private Byte RealTimeUpdated;
    private String NstCpyRef;
    private Byte UnitaryNest;
    private String Var1;
    private String Var2;
    private String Var3;
    private String Var4;
    private String Var5;
    private LocalDateTime CamLastDate;
    private String WrkCfg;
    private LocalDateTime RequiredDate;
    private LocalDateTime ScheduledStart;
    private Integer Priority;
    private String Name;
    private Integer MStateCloudStatus;
    private Integer CuttingStatus;
    private Integer CutQuantity;
    private String ExternalIndex;
    private Byte Automatic;
    private Integer RecState;
    private LocalDateTime CrtDate;
    private LocalDateTime LastDate;
    private String CrtUser;
    private String LastUser;
    private String Owner;
    private String RecEnt;
    private String RecOU;
    private Integer RecSec;
    private Integer CntID;
    private Integer RecID;
}
