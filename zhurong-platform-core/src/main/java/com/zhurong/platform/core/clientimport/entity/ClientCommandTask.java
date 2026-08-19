package com.zhurong.platform.core.clientimport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.base.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 通用客户端命令记录。载荷保持 JSON 透明，core 不依赖任何客户业务类型。
 */
@Getter
@Setter
@TableName("Zhurong_ClientCommandTask")
public class ClientCommandTask extends BaseEntity {

    @TableField("CommandId")
    private String commandId;

    @TableField("CommandType")
    private String commandType;

    @TableField("TargetClientId")
    private String targetClientId;

    @TableField("Status")
    private String status;

    @TableField("PayloadJson")
    private String payloadJson;

    @TableField("ResultJson")
    private String resultJson;

    @TableField("ResultMessage")
    private String resultMessage;

    @TableField("PublishTime")
    private LocalDateTime publishTime;

    @TableField("FinishTime")
    private LocalDateTime finishTime;
}
