package com.zhurong.platform.base.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;

@Getter
@Setter
public abstract class BasePageQuery implements Serializable {

    /**
     * 当前页
     */
    private Long current;
    public Long getCurrent(){
        return Optional.ofNullable(current).orElse(page);
    }
    private Long page = 1L;

    /**
     * 每页条数
     */
    private Long size;
    private Long pageSize = 10L;
    public Long getPageSize(){
        return Optional.ofNullable(size).orElse(pageSize);
    }

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 排序方向 asc / desc
     */
    private String orderDirection = "desc";
}
