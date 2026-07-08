package com.zhurong.platform.core.clientimport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.base.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@TableName("Zhurong_ClientDispatchTask")
public class ClientDispatchTask extends BaseEntity {

    @TableField("TaskId")
    private String taskId;

    @TableField("RequestId")
    private String requestId;

    @TableField("BusinessType")
    private String businessType;

    @TableField("TargetClientId")
    private String targetClientId;

    @TableField("Status")
    private String status;

    @TableField("RetryCount")
    private Integer retryCount;

    @TableField("RoutingKey")
    private String routingKey;

    @TableField("RequestSnapshot")
    private String requestSnapshot;

    @TableField("ResponseSnapshot")
    private String responseSnapshot;

    @TableField("ErrorMessage")
    private String errorMessage;

    @TableField("PublishTime")
    private LocalDateTime publishTime;

    @TableField("ReceiveTime")
    private LocalDateTime receiveTime;

    @TableField("FinishTime")
    private LocalDateTime finishTime;
}
