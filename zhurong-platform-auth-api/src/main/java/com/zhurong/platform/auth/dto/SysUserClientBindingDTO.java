package com.zhurong.platform.auth.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/** 登录账号与 Windows 客户端主机名的绑定参数；clientId 为空表示解除绑定。 */
@Data
public class SysUserClientBindingDTO implements Serializable {

    @Size(max = 128, message = "客户端ID长度不能超过128个字符")
    private String clientId;
}
