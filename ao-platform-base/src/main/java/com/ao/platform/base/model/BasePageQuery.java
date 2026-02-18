package com.ao.platform.base.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class BasePageQuery implements Serializable {

    /**
     * 当前页
     */
    private Long current = 1L;
    private Long page = 1L;

    /**
     * 每页条数
     */
    private Long size = 10L;
    private Long pageSize = 10L;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 排序方向 asc / desc
     */
    private String orderDirection = "desc";
}
