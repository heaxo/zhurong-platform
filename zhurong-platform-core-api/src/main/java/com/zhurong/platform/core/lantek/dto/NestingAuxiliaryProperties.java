package com.zhurong.platform.core.lantek.dto;

import lombok.Data;

/*
 * @Author zhurong
 * @Description NestingAuxiliaryProperties
 * @Date 2026/4/15 22:15
 **/
@Data
public class NestingAuxiliaryProperties
{
    public String nstRef;
    /// 位移长度
    /// NB001
    public Double displacementLength;
    /// 无坡口切割长度
    /// NB002
    public Double bevelessCuttingLength;
    /// 穿孔数
    /// NC001
    public Double numberOfPerforations;
    /// 位移时长（s）
    /// ND001
    public Double displacementDuration;
    /// 普通切割时长（s）
    /// ND002
    public Double normalCuttingDuration;
    /// 穿孔时长（s）
    /// ND008
    public Double perforationDuration;
    /// 上下料时长（Auxiliar time （s））
    /// ND024
    public Double auxiliarTime;
    /// 总加工时长（s）
    /// ND025
    public Double totalProcessingTime;
    /// 材料成本
    /// NE001
    public Double costOfMaterial;
    /// 机器时间成本
    /// NE002
    public Double costOfMachineTime;
    /// 消耗品成本
    /// NE003
    public Double costOfConsumable;
    /// 规划成本
    /// NE004
    public Double costOfPlanning;
    /// 总成本
    /// NE006
    public Double totalCost;
    /// 上料时长（s）
    /// NH001	Auxiliar time: Sheet upload 
    public Double sheetUploadAuxiliarTime;
    /// 下料时长（s）
    /// NH004	Auxiliar time: Sheet download 
    public Double sheetDownloadAuxiliarTime;
}
