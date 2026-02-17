package com.ao.platform.auth.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class SysUserRoleVO implements Serializable {

    private Long userId;
    private Object roleId;

}
