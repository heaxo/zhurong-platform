package com.ao.platform.auth.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class SysRoleMenuVO implements Serializable {

    private Object roleId;
    private Integer menuId;

}
