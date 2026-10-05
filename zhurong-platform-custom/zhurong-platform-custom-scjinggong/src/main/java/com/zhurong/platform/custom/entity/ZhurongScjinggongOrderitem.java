package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 *
 * @author me
 * @since 2026-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Zhurong_Scjinggong_OrderItem")
public class ZhurongScjinggongOrderitem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @TableField("invalid_state")
    private Boolean invalidState;

    /**
     *
     */
    @TableField("prd_ref")
    private String prdRef;

    /**
     *
     */
    @TableField("wrk_ref")
    private String wrkRef;

    /**
     *
     */
    @TableField("cus_ref")
    private String cusRef;

    /**
     *
     */
    @TableField("ord_ref")
    private String ordRef;

    /**
     *
     */
    @TableField("quantity")
    private Integer quantity;

    /**
     * 订单交货日期
     */
    @TableField("rdate")
    private LocalDateTime rdate;

    /**
     * 加工中心编码
     */
    @TableField("udata1")
    private String udata1;

    /**
     * U8生产订单号
     */
    @TableField("udata2")
    private String udata2;

    /**
     * 收料仓库编码
     */
    @TableField("udata3")
    private String udata3;

    /**
     * 收料仓库名称
     */
    @TableField("udata4")
    private String udata4;

    /**
     * 工序行号
     */
    @TableField("udata5")
    private String udata5;

    /**
     * 工序编码
     */
    @TableField("udata6")
    private String udata6;

    /**
     * 工序名称
     */
    @TableField("udata7")
    private String udata7;

    /**
     * 班组编码
     */
    @TableField("udata8")
    private String udata8;

    /**
     * 班组名称
     */
    @TableField("udata9")
    private String udata9;

    /**
     * 计划完工时间
     */
    @TableField("udata10")
    private String udata10;

    /**
     * 订单变更后交期
     */
    @TableField("udata11")
    private String udata11;

    /**
     * 工单状态
     */
    @TableField("udata12")
    private String udata12;
}
