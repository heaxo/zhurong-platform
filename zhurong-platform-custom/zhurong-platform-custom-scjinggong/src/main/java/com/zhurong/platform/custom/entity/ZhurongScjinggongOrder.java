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
@TableName("Zhurong_Scjinggong_Order")
public class ZhurongScjinggongOrder extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
            @TableField("invalid_state")
            private Boolean invalidState;

        /**
        * 批次号
        */
            @TableField("order_code")
            private String orderCode;

        /**
        * 批次名
        */
            @TableField("order_name")
            private String orderName;

        /**
        * 
        */
            @TableField("udata1")
            private String udata1;

        /**
        * 
        */
            @TableField("udata2")
            private String udata2;

        /**
        * 
        */
            @TableField("udata3")
            private String udata3;

        /**
        * 
        */
            @TableField("udata4")
            private String udata4;

        /**
        * 
        */
            @TableField("udata5")
            private String udata5;
}
