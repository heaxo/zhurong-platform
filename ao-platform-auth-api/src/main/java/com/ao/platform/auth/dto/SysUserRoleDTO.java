package com.ao.platform.auth.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class SysUserRoleDTO implements Serializable {

    private Long userId;
    private Object roleId;

}
