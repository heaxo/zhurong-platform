package com.zhurong.platform.core.lantek.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富查询装配开关
 */
@Data
@Accessors(chain = true)
public class RelationLoadPlan {

    /** 是否补套料零件 */
    private boolean includeNestParts;

    /** 是否补套料余料 */
    private boolean includeNestRemnants;

    /** 是否补套料工艺统计 */
    private boolean includeNestMetrics;

    /** 是否补套料输出文档 */
    private boolean includeNestFiles;

    /** 是否补单项主数据 */
    private boolean includePartMaster;
    /** 是否补计划主数据 */
    private boolean includePlanMaster;

    /** 是否补单项辅助数据 */
    private boolean includePartExtra;

    /** 是否补单项文档 */
    private boolean includePartFiles;

    /** 是否补作业主信息 */
    private boolean includeJobCard;

    /** 是否补作业完整路径 */
    private boolean includeJobFullPath;
}