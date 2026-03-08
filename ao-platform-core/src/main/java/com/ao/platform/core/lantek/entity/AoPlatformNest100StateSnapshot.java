package com.ao.platform.core.lantek.entity;

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
* @author heao
* @since 2026-03-05
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("AO_PLATFORM_NEST100_STATE_SNAPSHOT")
public class AoPlatformNest100StateSnapshot extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
        @TableId(value = "RecID",type = IdType.INPUT)
            private Integer RecID;

        /**
        * 
        */
        @TableField("MState")
            private Integer MState;

        /**
        * 
        */
        @TableField("LastDate")
            private LocalDateTime LastDate;

        /**
        * 
        */
        @TableField("SyncTime")
            private LocalDateTime SyncTime;
}
