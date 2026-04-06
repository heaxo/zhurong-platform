package com.zhurong.platform.custom.erp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/*
 * @Author zhurong
 * @Description LkPmOrder
 * @Date 2026/4/6 15:15
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("LK_PM_ORDER")
public class LkPmOrder {
    @TableField("BELPOS_ID")
    private String belposId;
}
