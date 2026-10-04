package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* 
*
* @author me
* @since 2026-10-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Zhurong_Scjinggong_BasePart")
public class ZhurongScjinggongBasepart extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
            @TableField("invalid_state")
            private Boolean invalidState;

        /**
        * 物料编码
        */
            @TableField("prd_ref")
            private String prdRef;

        /**
        * 物料名称
        */
            @TableField("prd_name")
            private String prdName;

        /**
        * 机床
        */
            @TableField("wrk_ref")
            private String wrkRef;

        /**
        * 材质
        */
            @TableField("mat_ref")
            private String matRef;

        /**
        * 厚度
        */
            @TableField("thickness")
            private Float thickness;

        /**
        * 数量
        */
            @TableField("quantity")
            private Integer quantity;

        /**
        * 层级
        */
            @TableField("udata1")
            private String udata1;

        /**
        * 客户件号
        */
            @TableField("udata2")
            private String udata2;

        /**
        * 物料参数
        */
            @TableField("udata3")
            private String udata3;

        /**
        * 工艺路线集合
        */
            @TableField("udata4")
            private String udata4;

        /**
        * 子件物料编码
        */
            @TableField("udata5")
            private String udata5;

        /**
        * 子件物料名称
        */
            @TableField("udata6")
            private String udata6;

        /**
        * 子件物料规格
        */
            @TableField("udata7")
            private String udata7;

        /**
        * 子件物料材质
        */
            @TableField("udata8")
            private String udata8;

        /**
        * 图纸路径
        */
            @TableField("drawing_path")
            private String drawingPath;

        /**
        * 原始图纸路径
        */
            @TableField("raw_drawing_path")
            private String rawDrawingPath;
}
