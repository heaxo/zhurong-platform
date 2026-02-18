package com.ao.platform.base.api;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class BasePageQuery implements Serializable {

    /**
     * 当前页
     */
    private long current = 1;
    private long page = 1;

    /**
     * 每页条数
     */
    private long size = 10;
    private long pageSize = 10;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 排序方向 asc / desc
     */
    private String orderDirection = "desc";
}
