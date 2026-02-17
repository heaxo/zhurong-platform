package com.ao.platform.auth.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class SysUserVO implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String realName;
    private Object deptId;
    private Integer status;
    private LocalDateTime createTime;

}
