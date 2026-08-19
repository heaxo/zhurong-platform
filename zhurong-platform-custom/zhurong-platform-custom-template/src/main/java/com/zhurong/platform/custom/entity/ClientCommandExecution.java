package com.zhurong.platform.custom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhurong.platform.custom.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** 客户端透明代理命令的幂等执行记录。 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("Zhurong_ClientCommandExecution")
public class ClientCommandExecution extends BaseEntity {

    @TableField("command_id")
    private String commandId;

    @TableField("command_type")
    private String commandType;

    @TableField("status")
    private String status;

    @TableField("message")
    private String message;

    @TableField("result_json")
    private String resultJson;
}
