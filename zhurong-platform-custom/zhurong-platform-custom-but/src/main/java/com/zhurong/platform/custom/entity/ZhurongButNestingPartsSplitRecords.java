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
* @since 2026-04-22
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Zhurong_But_Nesting_Parts_Split_Records")
public class ZhurongButNestingPartsSplitRecords extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
            @TableField("nst_ref")
            private String nstRef;

        /**
        * 
        */
            @TableField("mno_ref")
            private String mnoRef;
        /**
        *
        */
            @TableField("org_mno_ref")
            private String orgMnoRef;

        /**
        * 
        */
            @TableField("opr_id")
            private Integer oprId;

        /**
        * 
        */
            @TableField("quantity")
            private Integer quantity;

        /**
        * 
        */
            @TableField("remark")
            private String remark;

        /**
        * 
        */
            @TableField("ord_ref")
            private String ordRef;

        /**
        * 
        */
            @TableField("rec_id")
            private Integer recId;

    /**
     *
     */
    @TableField("prd_ref")
    private String prdRef;
}
