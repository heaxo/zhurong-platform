package com.ao.platform.auth.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class SysDeptVO implements Serializable {

    private Object id;
    private Object pid;
    private String name;
    private Integer status;
    private LocalDateTime createTime;
    private String remark;

}
