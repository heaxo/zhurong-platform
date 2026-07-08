package com.zhurong.platform.core.clientimport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.base.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@TableName("Zhurong_ClientRegistry")
public class ClientRegistry extends BaseEntity {

    @TableField("ClientId")
    private String clientId;

    @TableField("UserName")
    private String userName;

    @TableField("ClientVersion")
    private String clientVersion;

    @TableField("Status")
    private String status;

    @TableField("LastHeartbeatTime")
    private LocalDateTime lastHeartbeatTime;
}
