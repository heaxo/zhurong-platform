package com.ao.platform.auth.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  DTO
*/
@Data
public class SysDeptDTO implements Serializable {

    private Object id;
    private Object pid;
    private String name;
    private Integer status;
    private LocalDateTime createTime;
    private String remark;

}
