package com.zhurong.platform.core.lantek.dto;

import lombok.Data;

/*
 * @Author zhurong
 * @Description SheetPartsAuxiliaryData
 * @Date 2026/4/15 22:02
 **/
@Data
public class SheetPartsAuxiliaryData {
    public String prdRef;
    /// 位移长度
    /// NB001
    public Double displacementLength;
    /// 冲压次数（Number of punches (no nibbling)）
    /// NC012
    public Double numberOfPunches;
    /// 零件冲压次数
    /// NC015
    public Double partNumberOfPunches;
    /// 位移时长（s）
    /// ND001
    public Double displacementDuration;
    /// 零件冲压时长（s）
    /// ND020	Number of punches (no nibbling)
    public Double partPunchesDuration;
    /// 零件穿孔时长（s）
    /// ND023	Time of punch changes
    public Double partPerforationDuration;
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
    /// 零件带偏移的矩形重量（kg）
    /// NG004	Rectangular weight with offset
    public Double partRectangularWeightWithOffset;
    /// 零件带偏移的外部重量（kg）
    /// NG005	External weight with offset
    public Double externalWeightWithOffset;
    /// 零件带偏移的实际重量（kg）
    /// NG006	Real weight with offset
    public Double realWeightWithOffset;
    /// 偏移量（mm）
    /// NG007	Offset	
    public Double offset;
    /// 最小孔半径（mm）
    /// NG008	Minimum radius for holes
    public Double minimumRadiusForHoles;
    /// 零件面积加的最大比例（mm）
    /// NG009	Maximum part area to be increased percentage
    public Double maximumPartAreaToBeIncreasedPercentage;
}
