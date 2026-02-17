package com.ao.platform.auth.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class SysRoleMenuDTO implements Serializable {

    private Object roleId;
    private Integer menuId;

}
