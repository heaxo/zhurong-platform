package com.ao.platform.core.lantek.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.ao.platform.core.lantek.entity.BaseEntity;

/**
* 
*
* @author heao
* @since 2026-03-05
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("AO_PLATFORM_OUTBOX_EVENT")
public class AoPlatformOutboxEvent extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;


        /**
        * 
        */
        @TableField("EventId")
            private String EventId;

        /**
        * 
        */
        @TableField("EventType")
            private String EventType;

        /**
        * 
        */
        @TableField("AggregateId")
            private Integer AggregateId;

        /**
        * 
        */
        @TableField("OldState")
            private Integer OldState;

        /**
        * 
        */
        @TableField("NewState")
            private Integer NewState;

        /**
        * 
        */
        @TableField("SourceLastDate")
            private LocalDateTime SourceLastDate;

        /**
        * 
        */
        @TableField("Payload")
            private String Payload;

        /**
        * 
        */
        @TableField("Status")
            private Integer Status;

        /**
        * 
        */
        @TableField("RetryCount")
            private Integer RetryCount;

        /**
        * 
        */
        @TableField("NextRetryTime")
            private LocalDateTime NextRetryTime;
}
