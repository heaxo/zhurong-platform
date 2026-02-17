package com.ao.platform.auth.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class SysUserDTO implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String realName;
    private Object deptId;
    private Integer status;
    private LocalDateTime createTime;

}
